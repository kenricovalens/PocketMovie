package com.kenrico.pocketmovie.presentation.ui.detail

import androidx.lifecycle.*
import com.kenrico.pocketmoviecore.domain.model.Movie
import com.kenrico.pocketmoviecore.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    private val _isFavorite = MutableLiveData<Boolean?>()
    val isFavorite: LiveData<Boolean?> = _isFavorite

    fun checkFavorite(movieId: Int) {
        viewModelScope.launch {
            _isFavorite.value = movieUseCase.isFavorite(movieId)
        }
    }

    fun insertFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCase.insertMovieToFavorite(movie)
        }
    }

    fun deleteFavorite(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            movieUseCase.deleteMovieFromFavorite(movie)
        }
    }
}