package com.hiberus.magicandroidapp.domain.usecases

import com.hiberus.magicandroidapp.domain.CardRepository

class GetCardAutocompleteUseCase(
    private val cardRepository: CardRepository
) {

    suspend fun execute(cardName: String): List<String> {
        return cardRepository.getCardsAutocomplete(cardName)
    }

}