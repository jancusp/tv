package com.example.tv.di

import com.example.api.di.NetworkModule
import com.example.tv.app.CustomApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class,
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        MainFragmentBuildersModule::class,
        ViewModelFactoryModule::class
    ]
)
interface ApplicationComponent {

    fun inject(customApplication: CustomApplication)

    @Component.Builder
    interface Builder {

        fun build(): ApplicationComponent

        @BindsInstance
        fun customApplicationBind(customApplication: CustomApplication): Builder
    }
}