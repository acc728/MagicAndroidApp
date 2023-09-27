package com.hiberus.magicandroidapp.domain.usecases

import com.hiberus.magicandroidapp.domain.CardRepository
import com.hiberus.magicandroidapp.model.Card

class DeleteCardUseCase(
    private val cardRepository: CardRepository
) {

    suspend fun execute(card: Card) {
        cardRepository.deleteCard(card)
    }

}