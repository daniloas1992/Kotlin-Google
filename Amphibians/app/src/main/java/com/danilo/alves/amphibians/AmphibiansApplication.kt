package com.danilo.alves.amphibians

import android.app.Application
import com.danilo.alves.amphibians.data.AppContainer
import com.danilo.alves.amphibians.data.DefaultContainer

class AmphibiansApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer()
    }
}