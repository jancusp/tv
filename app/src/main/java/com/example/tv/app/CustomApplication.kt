package com.example.tv.app

import android.app.Activity
import android.app.Application
import com.example.api.di.NetworkModule
import com.example.tv.di.ApplicationComponent
import com.example.tv.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.DaggerApplication
import javax.inject.Inject

class CustomApplication : Application(), HasAndroidInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

//    private val networkModule: NetworkModule by lazy {
//        NetworkModule(this)
//    }

    override fun onCreate() {
        super.onCreate()

        DaggerApplicationComponent.builder()
            .customApplicationBind(this)
            .build()
            .inject(this)

    }


    override fun androidInjector(): AndroidInjector<Any>? = dispatchingAndroidInjector

    //    override fun getApplicationComponent(): ApplicationComponent {
//        return DaggerApplicationComponent.builder()
//            .networkModule(networkModule)
//            .build()
//    }
}