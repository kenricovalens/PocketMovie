package com.kenrico.pocketmoviecore.data.source.local.room

import androidx.room.*
import com.kenrico.pocketmoviecore.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMovie(listMovie: List<MovieEntity>)

    @Query("SELECT * FROM movies")
    fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Query("SELECT EXISTS(SELECT * FROM movies WHERE movie_id = :movieId)")
    suspend fun isFavorite(movieId: Int): Boolean

    @Insert
    suspend fun insertFavoriteMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteFavoriteMovie(movie: MovieEntity)

}