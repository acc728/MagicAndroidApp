package com.hiberus.magicandroidapp.domain.usecases

import com.hiberus.magicandroidapp.domain.CardRepository
import com.hiberus.magicandroidapp.model.Card

class GetCardUseCase(
    private val cardRepository: CardRepository
) {

    suspend fun execute(cardId: Int): Card {
        return cardRepository.getCard(cardId)
    }

}