package com.hiberus.magicandroidapp

import android.app.Application
import com.hiberus.magicandroidapp.di.baseModule
import com.hiberus.magicandroidapp.di.cardsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MagicApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MagicApplication)
            modules(baseModule, cardsModule).allowOverride(true)
        }
    }
}