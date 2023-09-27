package com.hiberus.magicandroidapp.data.card.local

import com.hiberus.magicandroidapp.data.database.AppDatabase
import com.hiberus.magicandroidapp.model.Card

class CardLocalImpl(
    private val appDatabase: AppDatabase
) {

    fun getCard(cardId: Int): Card {
        return appDatabase.cardsDao().getCard(cardId)
    }

    fun addCard(card: Card) {
        appDatabase.cardsDao().addCard(card)
    }

    fun deleteCard(card: Card) {
        appDatabase.cardsDao().deleteCard(card)
    }

    fun editCard(card: Card) {
        appDatabase.cardsDao().editCard(card)
    }

}