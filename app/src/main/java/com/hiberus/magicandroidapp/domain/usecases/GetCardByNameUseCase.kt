package com.hiberus.magicandroidapp.domain.usecases

import com.hiberus.magicandroidapp.domain.CardRepository
import com.hiberus.magicandroidapp.model.Card

class GetCardByNameUseCase(
    private val cardRepository: CardRepository
    ) {

        suspend fun execute(cardName: String): Card {
            return cardRepository.getCardByName(cardName)
        }

}
