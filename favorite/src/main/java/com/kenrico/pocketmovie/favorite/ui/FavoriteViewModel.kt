package com.kenrico.pocketmovie.favorite.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kenrico.pocketmoviecore.domain.usecase.MovieUseCase

class FavoriteViewModel constructor(movieUseCase: MovieUseCase): ViewModel() {

    val favoriteMovies = movieUseCase.getFavoriteMovies().asLiveData()
}