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

class RecyclerViewAdapter(
    private val movies: List<MovieHolder>,
    private val context: Context?,
    private var onItemListener: OnItemListener,
    private var favourites: MutableList<Int>
) :
    RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val poster = parent.inflate(R.layout.recycler_item)
        return MoviesViewHolder(poster, onItemListener, favourites)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentUrl: String = "https://image.tmdb.org/t/p/w500" +
                movies[position].movie.posterPath
        holder.bind(movies[position].movie, currentUrl)
    }
}

class MoviesViewHolder(
    private val view: View,
    private var onItemListener: OnItemListener,
    private var favourites: MutableList<Int>
) :
    RecyclerView.ViewHolder(view), View.OnClickListener {

    init {
        view.setOnClickListener(this)
    }

    fun bind(movie: MovieDetails, url: String) {
        view.movie_title.text = movie.title
        Glide.with(view)
            .load(url)
            .into(view.movie_poster)

        view.favourite_first.visibility =
            if (favourites.contains(movie.id)) View.VISIBLE else View.GONE
    }

    override fun onClick(p0: View?) {
        onItemListener.onItemClick(adapterPosition)
    }
}

interface OnItemListener {
    fun onItemClick(position: Int)
}