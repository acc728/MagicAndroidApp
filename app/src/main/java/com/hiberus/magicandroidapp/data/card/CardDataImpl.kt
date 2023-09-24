package com.hiberus.magicandroidapp.data.card

import com.hiberus.magicandroidapp.data.card.local.CardsLocalImpl
import com.hiberus.magicandroidapp.domain.CardsRepository

class CardDataImpl(
    private val cardsLocalImpl: CardsLocalImpl
): CardsRepository {



}