package com.hiberus.magicandroidapp.domain.usecases

import com.hiberus.magicandroidapp.domain.CardRepository
import com.hiberus.magicandroidapp.model.Card

class GetCardUseCase(
    private val cardRepository: CardRepository
) {

    fun execute(cardId: String): Card {
        return cardRepository.getCard(cardId)
    }

}