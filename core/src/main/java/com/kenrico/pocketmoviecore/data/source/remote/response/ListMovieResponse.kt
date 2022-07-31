package com.kenrico.pocketmoviecore.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListMovieResponse (

    @field:SerializedName("results")
    val listOfMovies: List<MovieResponse>
)