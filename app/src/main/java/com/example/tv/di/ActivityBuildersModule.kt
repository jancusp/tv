package com.example.tv.di

import com.example.api.di.NetworkModule
import com.example.tv.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
public abstract class ActivityBuildersModule {
    @ActivityScope
    @ContributesAndroidInjector(
        modules = [
            MainFragmentBuildersModule::class])
    abstract fun bindMainActivity(): MainActivity

}