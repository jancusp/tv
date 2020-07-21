package com.example.tv.ui.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api.data.MovieSearchResult
import com.example.api.service.FilmService
import com.example.tv.R
import com.example.tv.ui.adapter.RecyclerViewAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieListFragment : DaggerFragment() {

    @Inject
    lateinit var providerFactory: ViewModelProvider.Factory

    var _movies = MutableLiveData<MovieSearchResult>(MovieSearchResult())
    lateinit var movies: LiveData<MovieSearchResult>

    private lateinit var viewModel: MovieListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, providerFactory).get(MovieListViewModel::class.java)
//        val factory = MovieListViewModel.Factory()
//        viewModel = ViewModelProvider(this, factory)
//            .get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        initMovies(view)

        return view
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }

    private fun initMovies(view: View){
        val cols = 3
        view.movies_recycler.layoutManager = GridLayoutManager(context, cols)
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            view.movies_recycler.adapter = RecyclerViewAdapter(viewModel.movies.value!!.movies, context)
        })

    }

    override fun onResume() {
        super.onResume()
        viewModel.fetchFilms()
    }
}