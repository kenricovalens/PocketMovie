package com.kenrico.pocketmovie.presentation.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.kenrico.pocketmovie.R
import com.kenrico.pocketmoviecore.domain.model.Movie
import com.kenrico.pocketmovie.databinding.ActivityDetailMovieBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extraMovie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)

        if(extraMovie != null) {

            setupUi(extraMovie)

            detailViewModel.checkFavorite(extraMovie.movieId)

            detailViewModel.isFavorite.observe(this) {
                if(it != null) {
                    if(it) {
                        binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite))
                    } else {
                        binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_empty_favorite))
                    }
                }
            }

            binding.fabFavorite.setOnClickListener {
                if(detailViewModel.isFavorite.value == true) {
                    detailViewModel.deleteFavorite(extraMovie)
                    Toast.makeText(this, "Hapus dari favorit", Toast.LENGTH_SHORT).show()
                } else {
                    detailViewModel.insertFavorite(extraMovie)
                    Toast.makeText(this, "Masuk ke favorit", Toast.LENGTH_SHORT).show()
                }
                detailViewModel.checkFavorite(extraMovie.movieId)
            }
        }
    }

    private fun setupUi(extraMovie: Movie) {
        Glide.with(this)
            .load(extraMovie.moviePosterUrl)
            .into(binding.detailImgMoviePoster)

        binding.apply {
            detailTvMovieTitle.text = extraMovie.movieTitle
            detailTvReleaseDate.text = getString(R.string.item_movie_release_date, extraMovie.movieReleaseDate)
            detailTvRating.text = getString(R.string.detail_movie_score, extraMovie.movieScore, extraMovie.movieVoteCount)
            detailTvOverview.text = extraMovie.movieOverview
        }
    }

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

}

