package com.hiberus.magicandroidapp.di

import androidx.room.Room
import com.hiberus.magicandroidapp.data.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val baseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "cards_db"
        ).build()
    }
}

val cardsModule = module {
    //factory {}
}