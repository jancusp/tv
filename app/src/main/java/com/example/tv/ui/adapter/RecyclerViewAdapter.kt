package com.example.tv.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api.utils.inflate
import com.example.tv.R
import com.example.api.data.MovieHolder
import com.example.api.service.MovieDetails
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
        holder.bind(movies[position].movie)
    }
}

class MoviesViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun bind(movie: MovieDetails) {
        view.movie_title.text = movie.title
    }
}