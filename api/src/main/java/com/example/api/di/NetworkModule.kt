package com.example.api.di

import android.app.Application
import com.example.api.service.FilmService
import com.example.api.resource.StringsProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule() {

    @Provides
    @Singleton
    fun provideFilmService(): FilmService = FilmService()

}

//@Module
//class NetworkModule(val application: Application) {
//
//    @Provides
//    @Singleton
//    fun getFilms(
//        stringsProvider: StringsProvider
//    ): FilmService =
//        FilmService(stringsProvider)
//
//    @Provides
//    @Singleton
//    fun stringsProvider() = StringsProvider(application)
//
//}