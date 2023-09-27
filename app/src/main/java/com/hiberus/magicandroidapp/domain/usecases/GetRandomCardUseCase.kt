package com.hiberus.magicandroidapp.domain.usecases

import com.hiberus.magicandroidapp.domain.CardRepository
import com.hiberus.magicandroidapp.model.Card

class GetRandomCardUseCase(
    private val cardRepository: CardRepository
) {

    suspend fun execute(): Card {
        return cardRepository.getRandomCard()
    }

}