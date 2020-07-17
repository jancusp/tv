package com.example.tv.app

import android.app.Application
import com.example.api.di.NetworkModule
import com.example.tv.di.ApplicationComponent
import com.example.tv.di.DaggerApplicationComponent
import com.example.tv.di.provider.ApplicationComponentProvider

class CustomApplication : Application(),
    ApplicationComponentProvider {

    private val networkModule: NetworkModule by lazy {
        NetworkModule(this)
    }

    override fun getApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .networkModule(networkModule)
            .build()
    }
}