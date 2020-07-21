package com.example.api.service

import androidx.lifecycle.MutableLiveData
import com.example.api.data.MovieDetails
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
import com.example.api.data.MovieHolder
import com.example.api.data.MovieSearchResult
import com.example.api.data.SpecificMovieDetails

class FilmService {

    var client = OkHttpClient()
    var request = OkHttpRequest(client)

    private lateinit var movies: MovieSearchResult

    private lateinit var movieSearchResult: List<MovieDetails>

    fun fetchFilms(moviesResult: MutableLiveData<MovieSearchResult>) {
        moviesResult.postValue(MovieSearchResult())
        movies = MovieSearchResult()
        //request.GET(stringsProvider.getString(R.string.url_all_films),
        request.GET("https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&api_key=",
            object : Callback {
                override fun onResponse(call: Call, response: Response) {
                    val responseData = response.body()?.string()
                    try{
                        var json = JSONObject(responseData)
                        var filmsJson = json.getJSONArray("results")
                        movieSearchResult = parseFilms(filmsJson.toString())
                        movieSearchResult.forEach { movie ->
                            movies.movies.add(MovieHolder(movie))
                            moviesResult.postValue(movies)
                        }
                        println(movies)
                    } catch (e: JSONException){
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    TODO("Make Allert Dialog Peter")
                }
            })
    }

    fun fetchFilmById(id: Int) {
        //request.GET(String.format(stringsProvider.getString(R.string.url_specific_film), id),
        request.GET(
            "https://api.themoviedb.org/3/movie/$id?api_key=",
            object : Callback {

                override fun onResponse(call: Call, response: Response) {
                    val responseData = response.body()?.string()
                    val json = JSONObject(responseData)
                    try{
                        println(parseFilm(json.toString()))
                    } catch (e: JSONException){
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call, e: IOException) {
                    TODO("Make Allert Dialog Peter")
                }

            })
    }

    fun parseFilm(film: String): SpecificMovieDetails? {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<SpecificMovieDetails> = moshi.adapter(
            SpecificMovieDetails::class.java)
        val movie = adapter.fromJson(film)
        return movie
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