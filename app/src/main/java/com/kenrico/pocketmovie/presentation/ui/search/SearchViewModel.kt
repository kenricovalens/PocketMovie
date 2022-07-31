package com.kenrico.pocketmovie.presentation.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kenrico.pocketmoviecore.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val movieUseCase: MovieUseCase): ViewModel() {

    private val _searchQuery = MutableLiveData<String>()

    fun insertQuery(query: String) {
        _searchQuery.value = query
    }

    val searchedMovies = Transformations.switchMap(_searchQuery) {
        movieUseCase.searchMovie(it).asLiveData()
    }
}