package com.hiberus.magicandroidapp.domain

import com.hiberus.magicandroidapp.model.Card

interface CardRepository {

    fun getRandomCard(): Card

    fun getCardsAutocomplete(cardName: String): List<String>

    fun getCard(cardId: Int): Card

    fun addCard(card: Card)

    fun deleteCard(card: Card)

    fun editCard(card: Card)

}