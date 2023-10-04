package com.hiberus.magicandroidapp.domain.usecases

import com.hiberus.magicandroidapp.domain.CardRepository
import com.hiberus.magicandroidapp.model.Card

class AddCardUseCase(
    private val cardRepository: CardRepository
) {

    fun execute(card: Card) {
        cardRepository.addCard(card)
    }

}