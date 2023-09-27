package com.hiberus.magicandroidapp.domain

import com.hiberus.magicandroidapp.model.Card

interface CardRepository {

    suspend fun getRandomCard(): Card

    suspend fun getCardsAutocomplete(cardName: String): List<String>

    fun getCard(cardId: Int): Card

    fun addCard(card: Card)

    fun deleteCard(card: Card)

    fun editCard(card: Card)

}