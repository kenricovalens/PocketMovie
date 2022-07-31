package com.kenrico.pocketmoviecore.utils

import com.kenrico.pocketmoviecore.data.source.local.entity.MovieEntity
import com.kenrico.pocketmoviecore.data.source.remote.response.MovieResponse
import com.kenrico.pocketmoviecore.domain.model.Movie

object DataMapper {

    fun mapResponsesToDomain(input: List<MovieResponse>): List<Movie> {
        val movieList = mutableListOf<Movie>()
        input.map {
            val movie = Movie(
                movieId = it.id,
                movieTitle = it.title,
                movieOverview = it.overview,
                moviePosterUrl = "https://image.tmdb.org/t/p/w500" + it.posterUrl,
                movieReleaseDate = it.releaseDate,
                movieScore = it.voteScore,
                movieVoteCount = it.voteCount,
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                movieTitle = it.movieTitle,
                movieOverview = it.movieOverview,
                moviePosterUrl = it.moviePosterUrl,
                movieReleaseDate = it.movieReleaseDate,
                movieScore = it.movieScore,
                movieVoteCount = it.movieVoteCount,
            )
        }

    fun mapDomainToEntities(input: Movie) = MovieEntity(
        movieId = input.movieId,
        movieTitle = input.movieTitle,
        movieOverview = input.movieOverview,
        moviePosterUrl = input.moviePosterUrl,
        movieReleaseDate = input.movieReleaseDate,
        movieScore = input.movieScore,
        movieVoteCount = input.movieVoteCount,
    )
}