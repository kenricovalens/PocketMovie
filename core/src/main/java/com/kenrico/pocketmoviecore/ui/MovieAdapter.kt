package com.kenrico.pocketmoviecore.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kenrico.pocketmoviecore.domain.model.Movie
import com.kenrico.pocketmoviecore.R
import com.kenrico.pocketmoviecore.databinding.ItemMovieBinding

class MovieAdapter: ListAdapter<Movie, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class MovieViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)

        fun bind(data: Movie) {
            binding.apply {
                Glide.with(itemView)
                    .load(data.moviePosterUrl)
                    .placeholder(R.drawable.ic_baseline_broken_image_24)
                    .into(imgMoviePoster)

                itemTvTitle.text = data.movieTitle
                itemTvReleased.text = itemView.context.getString(R.string.item_movie_release_date, data.movieReleaseDate)
                itemTvScore.text = data.movieScore.toString()
            }

            itemView.setOnClickListener {
                val toDetailMovie = Intent(itemView.context, Class.forName("com.kenrico.pocketmovie.presentation.ui.detail.DetailMovieActivity"))
                toDetailMovie.putExtra(EXTRA_MOVIE, data)
                itemView.context.startActivity(toDetailMovie)
            }
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"

        val DIFF_CALLBACK: DiffUtil.ItemCallback<Movie> =
            object : DiffUtil.ItemCallback<Movie>() {
                override fun areItemsTheSame(oldUser: Movie, newUser: Movie): Boolean {
                    return oldUser.movieTitle == newUser.movieTitle
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldUser: Movie, newUser: Movie): Boolean {
                    return oldUser == newUser
                }
            }
    }
}