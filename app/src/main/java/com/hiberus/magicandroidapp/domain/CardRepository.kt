package com.hiberus.magicandroidapp.domain

import com.hiberus.magicandroidapp.model.Card

interface CardRepository {

    suspend fun getRandomCard(): Card

    suspend fun getCardsAutocomplete(cardName: String): List<String>

    suspend fun getCardByName(cardName: String): Card

    suspend fun getCardList(): List<Card>

    fun getCard(cardId: String): Card

    fun addCard(card: Card)

    fun deleteCard(card: Card)

    fun editCard(card: Card)

}