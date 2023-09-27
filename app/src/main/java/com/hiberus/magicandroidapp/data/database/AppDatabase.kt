package com.hiberus.magicandroidapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hiberus.magicandroidapp.data.card.local.dao.CardsDao
import com.hiberus.magicandroidapp.model.Card
import com.hiberus.magicandroidapp.model.Converters

@TypeConverters(Converters::class)
@Database(entities = [Card::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun cardsDao(): CardsDao
}