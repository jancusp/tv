package com.example.tv

import android.content.Context

class SharedPreferences(context: Context) {

    companion object {
        const val MY_PREF = "tv_preferences"
        const val SPECIFIC_MOVIE = "specific_movie"
        const val FAVOURITE_MOVIES = "favourite_movies"
    }

    val preferences = context.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)

    fun getFavouriteMovies(): MutableList<Int> {
        return preferences.getStringSet(FAVOURITE_MOVIES, emptySet())
            ?.mapNotNull { it.toIntOrNull() }
            ?.toMutableList() ?: mutableListOf()
    }

    fun setFavouriteMovies(favourites: MutableList<Int>) {
        preferences.edit().putStringSet(FAVOURITE_MOVIES, favourites.map { it.toString() }.toSet())
            .apply()
    }

    fun getSpecificMovie(): Int {
        return preferences.getInt(SPECIFIC_MOVIE, -1)
    }

    fun setSpecificMovie(id: Int) {
        preferences.edit().putInt(SPECIFIC_MOVIE, id).apply()
    }

}