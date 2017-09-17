package com.goldendevloper.retrofitapp

import android.app.Application
import com.goldendevloper.retrofitapp.uitls.AppRetrofit


/**
 * Created by Abdullah Hussein on 16/09/2017.
 * abdullah.hussein109@gmail.com
 */
class App : Application() {

    companion object {

        @get:Synchronized
        lateinit var instance: App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        AppRetrofit.initialize()
    }

}