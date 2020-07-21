package com.example.tv.app

import android.app.Application
import com.example.tv.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class CustomApplication : Application(), HasAndroidInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .customApplicationBind(this)
            .build()
            .inject(this)

    }

    override fun androidInjector(): AndroidInjector<Any>? = dispatchingAndroidInjector

}