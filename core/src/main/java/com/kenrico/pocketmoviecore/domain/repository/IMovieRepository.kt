package com.kenrico.pocketmoviecore.domain.repository

import com.kenrico.pocketmoviecore.data.Resource
import com.kenrico.pocketmoviecore.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getInitialMovies(): Flow<Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<Resource<List<Movie>>>

    suspend fun isFavorite(movieId: Int): Boolean

    suspend fun insertFavoriteMovie(movie: Movie)

    suspend fun deleteFavoriteMovie(movie: Movie)

    fun searchMovies(query: String): Flow<Resource<List<Movie>>>
}