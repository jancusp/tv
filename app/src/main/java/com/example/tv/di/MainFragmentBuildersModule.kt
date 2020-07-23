package com.example.tv.di

import com.example.tv.ui.movieInfo.MovieInfoFragment
import com.example.tv.ui.movieList.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    abstract fun contributeMovieInfoFragment(): MovieInfoFragment
}