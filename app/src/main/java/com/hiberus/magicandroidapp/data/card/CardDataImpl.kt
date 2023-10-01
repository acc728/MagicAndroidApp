package com.hiberus.magicandroidapp.data.card

import com.hiberus.magicandroidapp.data.card.local.CardLocalImpl
import com.hiberus.magicandroidapp.data.card.remote.CardRemoteImpl
import com.hiberus.magicandroidapp.domain.CardRepository
import com.hiberus.magicandroidapp.model.Card

class CardDataImpl(
    private val cardLocalImpl: CardLocalImpl,
    private val cardRemoteImpl: CardRemoteImpl
): CardRepository {
    override suspend fun getRandomCard(): Card {
        return cardRemoteImpl.getRandomCard()
    }

    override suspend fun getCardsAutocomplete(cardName: String): List<String> {
        return cardRemoteImpl.getCardsAutocomplete(cardName)
    }

    override suspend fun getCardByName(cardName: String): Card {
        return cardRemoteImpl.getCardByName(cardName)
    }

    override fun getCard(cardId: Int): Card {
        return cardLocalImpl.getCard(cardId)
    }

    override fun addCard(card: Card) {
        cardLocalImpl.addCard(card)
    }

    override fun deleteCard(card: Card) {
        cardLocalImpl.deleteCard(card)
    }

    override fun editCard(card: Card) {
        cardLocalImpl.editCard(card)
    }


}