package com.kenrico.pocketmovie.favorite.di

import android.content.Context
import com.kenrico.pocketmovie.favorite.ui.FavoriteFragment
import com.kenrico.pocketmovie.presentation.ui.di.FavoriteModule
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModule::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModule: FavoriteModule): Builder
        fun build(): FavoriteComponent
    }
}