package com.example.tv.di

import com.example.api.di.NetworkModule
import com.example.tv.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class
    ]
)
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}