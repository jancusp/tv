package com.example.tv.di

import com.example.tv.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuildersModule::class])
    abstract fun bindMainActivity(): MainActivity

}