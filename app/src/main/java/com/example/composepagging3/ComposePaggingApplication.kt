package com.example.composepagging3

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ComposePaggingApplication : Application(){

    override fun onCreate() {
        super.onCreate()
    }

}