package com.kenrico.pocketmoviecore.data

import com.kenrico.pocketmoviecore.BuildConfig
import com.kenrico.pocketmoviecore.data.source.local.room.MovieDao
import com.kenrico.pocketmoviecore.data.source.remote.network.ApiService
import com.kenrico.pocketmoviecore.domain.model.Movie
import com.kenrico.pocketmoviecore.domain.repository.IMovieRepository
import com.kenrico.pocketmoviecore.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
): IMovieRepository {

    override fun getInitialMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())

        try {
            val response = apiService.getNowPlayingMovies(BuildConfig.API_KEY)
            val listData = DataMapper.mapResponsesToDomain(response.listOfMovies)

            if(listData.isNotEmpty()) {
                emit(Resource.Success(data = listData))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    override fun getFavoriteMovies(): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())

        try {
            movieDao.getFavoriteMovies().collect {
                val listMovie = DataMapper.mapEntitiesToDomain(it)
                emit(Resource.Success(listMovie))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return movieDao.isFavorite(movieId)
    }

    override suspend fun insertFavoriteMovie(movie: Movie) {
        val movieEntity = DataMapper.mapDomainToEntities(movie)
        movieDao.insertFavoriteMovie(movieEntity)
    }

    override suspend fun deleteFavoriteMovie(movie: Movie) {
        val movieEntity = DataMapper.mapDomainToEntities(movie)
        movieDao.deleteFavoriteMovie(movieEntity)
    }

    override fun searchMovies(query: String): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())

        try {
            val response = apiService.searchMovies(BuildConfig.API_KEY, query)
            val listData = DataMapper.mapResponsesToDomain(response.listOfMovies)

            emit(Resource.Success(listData))
        } catch (e: Exception) {
            emit(Resource.Error(e.toString()))
        }
    }
}