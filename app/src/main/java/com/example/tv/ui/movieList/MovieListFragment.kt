package com.example.tv.ui.movieList

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api.data.MovieSearchResult
import com.example.tv.OnBackPressed
import com.example.tv.R
import com.example.tv.SharedPreferences
import com.example.tv.ui.adapter.OnItemListener
import com.example.tv.ui.adapter.RecyclerViewAdapter
import com.example.tv.ui.movieInfo.MovieInfoFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import kotlinx.android.synthetic.main.recycler_item.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : DaggerFragment(), OnItemListener, OnBackPressed {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, providerFactory).get(MovieListViewModel::class.java)
        context?.let { viewModel.initSharedPreferences(it) }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        initMovies(view)
        (activity as AppCompatActivity).supportActionBar?.title = "Movies"
        return view
    }

    private fun initMovies(view: View) {
        val cols = 3
        view.movies_recycler.layoutManager = GridLayoutManager(context, cols)
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            view.movies_recycler.adapter =
                RecyclerViewAdapter(viewModel.movies.value!!.movies, context, this, viewModel.sharedPreferences.getFavouriteMovies())
        })


    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchFilms()
    }

    override fun onItemClick(position: Int) {

        viewModel.sharedPreferences.setSpecificMovie(viewModel.movies.value!!.movies[position].movie.id)
        parentFragmentManager.beginTransaction()
        //childFragmentManager.beginTransaction()
            .replace(R.id.main_container, MovieInfoFragment())
            .commit()
    }

    override fun onBackPressed() {
        activity?.supportFragmentManager?.popBackStack()
    }
}