package com.hiberus.magicandroidapp.data.card.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hiberus.magicandroidapp.model.Card

@Dao
interface CardsDao {

    @Query("SELECT * FROM cards")
    fun getCardList(): List<Card>

    @Query("SELECT * FROM cards WHERE id = :cardId LIMIT 1")
    fun getCard(cardId: Int): Card

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCard(card: Card)

    @Delete
    fun deleteCard(card: Card)

    @Update
    fun editCard(card: Card)

}