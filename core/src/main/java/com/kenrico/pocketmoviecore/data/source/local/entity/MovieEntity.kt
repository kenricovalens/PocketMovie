package com.kenrico.pocketmoviecore.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieEntity(

    @PrimaryKey
    @ColumnInfo(name = "movie_id")
    val movieId: Int,

    @ColumnInfo(name = "movie_title")
    val movieTitle: String,

    @ColumnInfo(name = "movie_overview")
    val movieOverview: String,

    @ColumnInfo(name = "movie_poster_url")
    val moviePosterUrl: String,

    @ColumnInfo(name = "movie_release_date")
    val movieReleaseDate: String,

    @ColumnInfo(name = "movie_score")
    val movieScore: Double,

    @ColumnInfo(name = "movie_vote_count")
    val movieVoteCount: Int,

)