package com.kenrico.pocketmovie.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kenrico.pocketmoviecore.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {
    val listMovies = movieUseCase.getInitialMovies().asLiveData()

    // For testing purpose
    @JvmName("getListMovies1")
    fun getListMovies() = movieUseCase.getInitialMovies().asLiveData()
}