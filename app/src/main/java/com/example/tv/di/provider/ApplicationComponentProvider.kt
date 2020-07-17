package com.example.tv.di.provider

import com.example.tv.di.ApplicationComponent

interface ApplicationComponentProvider {
    fun getApplicationComponent(): ApplicationComponent
}