package com.example.tv.ui.movieList

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.api.data.MovieSearchResult
import com.example.api.service.FilmService
import com.example.tv.ui.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_movie_list.view.*
import javax.inject.Inject

class MovieListViewModel @Inject constructor() : ViewModel() {
    var _movies = MutableLiveData<MovieSearchResult>(MovieSearchResult())
    var movies: LiveData<MovieSearchResult> = _movies

    @Inject
    lateinit var filmService: FilmService

    fun fetchFilms(){
        filmService.fetchFilms(_movies)
    }
}