package com.hiberus.magicandroidapp.data.card.remote

import com.hiberus.magicandroidapp.data.remote.CardMagicService
import com.hiberus.magicandroidapp.model.Card

class CardRemoteImpl(
    private val cardMagicService: CardMagicService
) {

    suspend fun getRandomCard(): Card {
        return cardMagicService.getRandomCard()
    }

    suspend fun getCardsAutocomplete(cardName: String): List<String> {
        return cardMagicService.getCardsAutocomplete(cardName).data
    }

    suspend fun getCardByName(cardName: String): Card {
        return cardMagicService.getCardByName(cardName)
    }
}