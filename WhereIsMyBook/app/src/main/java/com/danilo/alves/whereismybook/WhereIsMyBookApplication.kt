package com.danilo.alves.whereismybook

import android.app.Application
import com.danilo.alves.whereismybook.data.AppContainer
import com.danilo.alves.whereismybook.data.DefaultContainer

class WhereIsMyBookApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer()
    }
}