package com.example.api.di

import com.example.api.service.FilmService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideFilmService(): FilmService = FilmService()
}
