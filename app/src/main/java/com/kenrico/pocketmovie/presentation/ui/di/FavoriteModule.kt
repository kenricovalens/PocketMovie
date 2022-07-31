package com.kenrico.pocketmovie.presentation.ui.di

import com.kenrico.pocketmoviecore.domain.usecase.MovieUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModule {

    fun provideMovieUseCase(): MovieUseCase
}