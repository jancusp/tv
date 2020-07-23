package com.example.api.service

import androidx.lifecycle.MutableLiveData
import com.example.api.data.*
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

class FilmService {
    var client = OkHttpClient().newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .build()
    var request = OkHttpRequest(client)

    private lateinit var movies: MovieSearchResult
    private lateinit var movieSearchResult: List<MovieDetails>

    private lateinit var movie: SpecificMovieResult
    private lateinit var movieResult: SpecificMovieDetails

    fun fetchFilms(moviesResult: MutableLiveData<MovieSearchResult>) {
        moviesResult.postValue(MovieSearchResult())
        movies = MovieSearchResult()
        request.GET("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=",
            object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val responseData = response.body()?.string()
                    try {
                        var json = JSONObject(responseData)
                        var filmsJson = json.getJSONArray("results")
                        movieSearchResult = parseFilms(filmsJson.toString())
                        movieSearchResult.forEach { movie ->
                            movies.movies.add(MovieHolder(movie))
                            moviesResult.postValue(movies)
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    TODO()
                }
            })
    }

    fun fetchFilmById(id: Int, specMovie: MutableLiveData<SpecificMovieResult>) {
        //request.GET(String.format(stringsProvider.getString(R.string.url_specific_film), id),
        request.GET(
            "https://api.themoviedb.org/3/movie/$id?api_key=",
            object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val responseData = response.body()?.string()
                    try {
                        var json = JSONObject(responseData)
                        movieResult = parseFilm(json.toString())
                        movie = SpecificMovieResult(SpecificMovieHolder(movieResult))
                        specMovie.postValue(movie)
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    TODO()
                }

            })
    }

    fun parseFilm(film: String): SpecificMovieDetails {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<SpecificMovieDetails> = moshi.adapter(
            SpecificMovieDetails::class.java
        )
        val movie = adapter.fromJson(film)
        return movie!!
    }

    fun parseFilms(films: String): List<MovieDetails> {
        val moshi: Moshi = Moshi.Builder().build()
        val listType = Types.newParameterizedType(List::class.java, MovieDetails::class.java)
        val adapter: JsonAdapter<List<MovieDetails>> = moshi.adapter(listType)
        val movies = adapter.fromJson(films)
        print(movies)
        return movies!!
    }

}