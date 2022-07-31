package com.kenrico.pocketmoviecore.domain.usecase

import com.kenrico.pocketmoviecore.data.Resource
import com.kenrico.pocketmoviecore.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {

    fun getInitialMovies(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovies(): Flow<Resource<List<Movie>>>
    suspend fun isFavorite(movieId: Int): Boolean
    suspend fun insertMovieToFavorite(movie: Movie)
    suspend fun deleteMovieFromFavorite(movie: Movie)
    fun searchMovie(query: String): Flow<Resource<List<Movie>>>
}