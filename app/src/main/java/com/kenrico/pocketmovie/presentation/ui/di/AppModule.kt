package com.kenrico.pocketmovie.presentation.ui.di

import com.kenrico.pocketmoviecore.domain.usecase.MovieInteractor
import com.kenrico.pocketmoviecore.domain.usecase.MovieUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton // viewmodelscoped
    abstract fun provideUseCase(movieInteractor: MovieInteractor): MovieUseCase
}