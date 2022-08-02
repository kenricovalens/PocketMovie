package com.kenrico.pocketmovie

import com.kenrico.pocketmoviecore.data.source.remote.network.ApiService
import com.kenrico.pocketmoviecore.data.source.remote.response.ListMovieResponse

class FakeApiService: ApiService {
    override suspend fun getNowPlayingMovies(apiKey: String): ListMovieResponse {
        return ListMovieResponse(
            listOf()
        )
    }

    override suspend fun searchMovies(apiKey: String, query: String): ListMovieResponse {
        return ListMovieResponse(
            listOf()
        )
    }
}