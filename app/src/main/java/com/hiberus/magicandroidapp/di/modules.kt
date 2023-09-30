package com.hiberus.magicandroidapp.di

import androidx.room.Room
import com.hiberus.magicandroidapp.data.card.CardDataImpl
import com.hiberus.magicandroidapp.data.card.local.CardLocalImpl
import com.hiberus.magicandroidapp.data.card.remote.CardRemoteImpl
import com.hiberus.magicandroidapp.data.database.AppDatabase
import com.hiberus.magicandroidapp.data.remote.ApiClient
import com.hiberus.magicandroidapp.data.remote.CardMagicService
import com.hiberus.magicandroidapp.domain.CardRepository
import com.hiberus.magicandroidapp.domain.usecases.AddCardUseCase
import com.hiberus.magicandroidapp.domain.usecases.DeleteCardUseCase
import com.hiberus.magicandroidapp.domain.usecases.EditCardUseCase
import com.hiberus.magicandroidapp.domain.usecases.GetCardAutocompleteUseCase
import com.hiberus.magicandroidapp.domain.usecases.GetCardUseCase
import com.hiberus.magicandroidapp.domain.usecases.GetRandomCardUseCase
import com.hiberus.magicandroidapp.presentation.viewmodel.CardsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val baseModule = module {
    single<AppDatabase> {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "cards_db"
        ).build()
    }

    single<CardMagicService> { ApiClient.retrofit.create(CardMagicService::class.java) }
}

val cardsModule = module {
    factory { CardLocalImpl(get()) }
    factory { CardRemoteImpl(get()) }

    factory<CardRepository> { CardDataImpl(get(), get()) }

    factory { AddCardUseCase(get()) }
    factory { DeleteCardUseCase(get()) }
    factory { EditCardUseCase(get()) }
    factory { GetCardUseCase(get()) }
    factory { GetCardAutocompleteUseCase(get()) }
    factory { GetRandomCardUseCase(get()) }

    viewModel { CardsViewModel(get(), get(), get(), get(), get(), get()) }
}