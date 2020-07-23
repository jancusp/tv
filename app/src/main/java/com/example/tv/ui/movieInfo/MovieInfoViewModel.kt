package com.example.tv.ui.movieInfo

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.api.data.SpecificMovieResult
import com.example.api.service.FilmService
import com.example.tv.SharedPreferences
import javax.inject.Inject

class MovieInfoViewModel @Inject constructor() : ViewModel() {

    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var filmService: FilmService

    private val _movie = MutableLiveData<SpecificMovieResult>()
    val movie: LiveData<SpecificMovieResult> = _movie

    fun initSharedPreferences(context: Context) {
        sharedPreferences =
            SharedPreferences(context)
    }

    fun fetchFilmById(id: Int){
        filmService.fetchFilmById(id, _movie)
    }
}