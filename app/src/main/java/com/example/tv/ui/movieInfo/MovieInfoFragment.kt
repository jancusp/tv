package com.example.tv.ui.movieInfo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.tv.OnBackPressed
import com.example.tv.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.movie_content.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MovieInfoFragment : DaggerFragment(), OnBackPressed {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, providerFactory).get(MovieInfoViewModel::class.java)
        context?.let { viewModel.initSharedPreferences(it) }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_info, container, false)
        initMovie(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val favs: MutableList<Int> = viewModel.sharedPreferences.getFavouriteMovies()
        val id: Int = viewModel.sharedPreferences.getSpecificMovie()
        val isFav: Boolean = favs.contains(id)
        if (isFav) {
            view.favourite_second.setImageResource(R.drawable.ic_baseline_star_24)
        } else {
            view.favourite_second.setImageResource(R.drawable.ic_baseline_star_border_24)
        }

        val favourite: FloatingActionButton = view.findViewById(R.id.favourite_second)
        favourite.setOnClickListener {
            if (isFav) {
                favs.remove(id)
                view.favourite_second.setImageResource(R.drawable.ic_baseline_star_border_24)
            } else {
                favs.add(id)
                view.favourite_second.setImageResource(R.drawable.ic_baseline_star_24)
            }
            viewModel.sharedPreferences.setFavouriteMovies(favs)

        }

    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchFilmById(viewModel.sharedPreferences.getSpecificMovie())
    }

    private fun initMovie(view: View){
        viewModel.fetchFilmById(viewModel.sharedPreferences.getSpecificMovie())
        val titleTextView: TextView = view.findViewById(R.id.specific_movie_title)
        val durationTextView: TextView = view.findViewById(R.id.real_duration)
        val posterImageView: ImageView = view.findViewById(R.id.specific_movie_poster)
        val backdropImageView: ImageView = view.findViewById(R.id.backdrop)

        viewModel.movie.observe(viewLifecycleOwner, Observer {
            titleTextView.text = viewModel.movie.value!!.movie.movie.title

            val dur: Int = viewModel.movie.value!!.movie.movie.runtime
            if (dur == 1) {
                durationTextView.text = dur.toString() + " minute"
            } else {
                durationTextView.text = dur.toString() + " minutes"
            }

            Glide.with(view)
                .load("https://image.tmdb.org/t/p/w500" +
                        viewModel.movie.value!!.movie.movie.posterPath)
                .into(posterImageView)
            Glide.with(view)
                .load("https://image.tmdb.org/t/p/w500" +
                        viewModel.movie.value!!.movie.movie.backDropPath)
                .into(backdropImageView)
            (activity as AppCompatActivity).supportActionBar?.title = viewModel.movie.value!!.movie.movie.title
        })
    }

    override fun onBackPressed() {
        activity?.supportFragmentManager?.popBackStack()
    }
}