package com.kenrico.pocketmoviecore.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int,
    val movieTitle: String,
    val movieOverview: String,
    val moviePosterUrl: String,
    val movieReleaseDate: String,
    val movieScore: Double,
    val movieVoteCount: Int,
): Parcelable