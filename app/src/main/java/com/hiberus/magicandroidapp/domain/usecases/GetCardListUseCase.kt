package com.hiberus.magicandroidapp.domain.usecases

import com.hiberus.magicandroidapp.domain.CardRepository
import com.hiberus.magicandroidapp.model.Card

class GetCardListUseCase(
    private val cardRepository: CardRepository
) {

    suspend fun execute(): List<Card> {
        return cardRepository.getCardList()
    }

}