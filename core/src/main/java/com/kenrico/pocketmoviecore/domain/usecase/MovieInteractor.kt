package com.kenrico.pocketmoviecore.domain.usecase

import com.kenrico.pocketmoviecore.data.MovieRepository
import com.kenrico.pocketmoviecore.data.Resource
import com.kenrico.pocketmoviecore.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieInteractor @Inject constructor(private val movieRepository: MovieRepository):
    MovieUseCase {

    override fun getInitialMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getInitialMovies()
    }

    override fun getFavoriteMovies(): Flow<Resource<List<Movie>>> {
        return movieRepository.getFavoriteMovies()
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return movieRepository.isFavorite(movieId)
    }

    override suspend fun insertMovieToFavorite(movie: Movie) =
        movieRepository.insertFavoriteMovie(movie)

    override suspend fun deleteMovieFromFavorite(movie: Movie) =
        movieRepository.deleteFavoriteMovie(movie)

    override fun searchMovie(query: String): Flow<Resource<List<Movie>>> {
        return movieRepository.searchMovies(query)
    }

}