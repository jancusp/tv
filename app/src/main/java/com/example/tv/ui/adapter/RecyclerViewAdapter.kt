package com.example.tv.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api.data.MovieDetails
import com.example.api.data.MovieHolder
import com.example.api.utils.inflate
import com.example.tv.R
import kotlinx.android.synthetic.main.recycler_item.view.*

class RecyclerViewAdapter(private val movies: List<MovieHolder>, private val context: Context?) :
    RecyclerView.Adapter<MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val poster = parent.inflate(R.layout.recycler_item)
        return MoviesViewHolder(poster)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentUrl: String =  "https://image.tmdb.org/t/p/w500" +
                movies[position].movie.posterPath
        holder.bind(movies[position].movie, currentUrl)

    }
}

class MoviesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movie: MovieDetails, url: String) {
        view.movie_title.text = movie.title
        Glide.with(view)
            .load(url)
            .into(view.movie_poster)
    }
}