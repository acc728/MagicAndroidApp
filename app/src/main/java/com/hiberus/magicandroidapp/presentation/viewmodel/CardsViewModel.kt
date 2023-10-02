package com.hiberus.magicandroidapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hiberus.magicandroidapp.domain.usecases.AddCardUseCase
import com.hiberus.magicandroidapp.domain.usecases.DeleteCardUseCase
import com.hiberus.magicandroidapp.domain.usecases.EditCardUseCase
import com.hiberus.magicandroidapp.domain.usecases.GetCardAutocompleteUseCase
import com.hiberus.magicandroidapp.domain.usecases.GetCardByNameUseCase
import com.hiberus.magicandroidapp.domain.usecases.GetCardListUseCase
import com.hiberus.magicandroidapp.domain.usecases.GetCardUseCase
import com.hiberus.magicandroidapp.domain.usecases.GetRandomCardUseCase
import com.hiberus.magicandroidapp.model.Card
import com.hiberus.magicandroidapp.model.ResourceState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


typealias RandomCardState = ResourceState<Card>
typealias CardAutocompleteState = ResourceState<List<String>>
typealias GetCardState = ResourceState<Card>
typealias AddCardState = ResourceState<Void?>
typealias DeleteCardState = ResourceState<Void?>
typealias EditCardState = ResourceState<Void?>
typealias GetCardByNameState = ResourceState<Card>
typealias GetCardListState = ResourceState<List<Card>>

class CardsViewModel(
    private val getCardAutocompleteUseCase: GetCardAutocompleteUseCase,
    private val getRandomCardUseCase: GetRandomCardUseCase,
    private val getCardUseCase: GetCardUseCase,
    private val addCardUseCase: AddCardUseCase,
    private val deleteCardUseCase: DeleteCardUseCase,
    private val editCardUseCase: EditCardUseCase,
    private val getCardByNameUseCase: GetCardByNameUseCase,
    private val getCardListUseCase: GetCardListUseCase
): ViewModel() {

    private val _randomCardLiveData = MutableLiveData<RandomCardState>()
    val randomCardLiveData: LiveData<RandomCardState> get() = _randomCardLiveData

    private val _cardAutocompleteLiveData = MutableLiveData<CardAutocompleteState>()
    val cardAutocompleteLiveData: LiveData<CardAutocompleteState> get() = _cardAutocompleteLiveData

    private val _getCardByNameLiveData = MutableLiveData<GetCardByNameState>()
    val getCardByNameLiveData: LiveData<GetCardByNameState> get() = _getCardByNameLiveData

    private val _getCardLiveData = MutableLiveData<GetCardState>()
    val getCardLiveData: LiveData<GetCardState> get() = _getCardLiveData

    private val _addCardLiveData = MutableLiveData<AddCardState>()
    val addCardLiveData: LiveData<AddCardState> get() = _addCardLiveData

    private val _deleteCardLiveData = MutableLiveData<DeleteCardState>()
    val deleteCardLiveData: LiveData<DeleteCardState> get() = _deleteCardLiveData

    private val _editCardLiveData = MutableLiveData<EditCardState>()
    val editCardLiveData: LiveData<EditCardState> get() = _editCardLiveData

    private val _getCardListLiveData = MutableLiveData<GetCardListState>()
    val getCardListLiveData: LiveData<GetCardListState> get() = _getCardListLiveData


    fun fetchRandomCard() {
        _randomCardLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val notes = getRandomCardUseCase.execute()

                withContext(Dispatchers.Main) {
                    _randomCardLiveData.value = ResourceState.Success(notes)
                    _randomCardLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _randomCardLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _randomCardLiveData.value = ResourceState.None()
                }
            }
        }
    }

    fun fetchAutocompleteCard(cardName: String) {
        _cardAutocompleteLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cardsNames = getCardAutocompleteUseCase.execute(cardName)

                withContext(Dispatchers.Main) {
                    _cardAutocompleteLiveData.value = ResourceState.Success(cardsNames)
                    _cardAutocompleteLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _cardAutocompleteLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _cardAutocompleteLiveData.value = ResourceState.None()
                }
            }
        }
    }

    fun fetchSearchCard(cardName: String) {
        _getCardByNameLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val card = getCardByNameUseCase.execute(cardName)

                withContext(Dispatchers.Main) {
                    _getCardByNameLiveData.value = ResourceState.Success(card)
                    _getCardByNameLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _getCardByNameLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _getCardByNameLiveData.value = ResourceState.None()
                }
            }
        }
    }

    fun fetchCardList() {
        _getCardListLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val cardList = getCardListUseCase.execute()

                withContext(Dispatchers.Main) {
                    _getCardListLiveData.value = ResourceState.Success(cardList)
                    _getCardListLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _getCardListLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _getCardListLiveData.value = ResourceState.None()
                }
            }
        }
    }

    fun fetchCard(cardId: Int) {
        _getCardLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val card = getCardUseCase.execute(cardId)

                withContext(Dispatchers.Main) {
                    _getCardLiveData.value = ResourceState.Success(card)
                    _getCardLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _getCardLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _getCardLiveData.value = ResourceState.None()
                }
            }
        }
    }

    fun addCard(card: Card) {
        _addCardLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                addCardUseCase.execute(card)

                withContext(Dispatchers.Main) {
                    _addCardLiveData.value = ResourceState.Success(null)
                    _addCardLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _addCardLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _addCardLiveData.value = ResourceState.None()
                }
            }
        }
    }

    fun deleteCard(card: Card) {
        _deleteCardLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleteCardUseCase.execute(card)

                withContext(Dispatchers.Main) {
                    _deleteCardLiveData.value = ResourceState.Success(null)
                    _deleteCardLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _deleteCardLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _deleteCardLiveData.value = ResourceState.None()
                }
            }
        }
    }

    fun editCard(card: Card) {
        _editCardLiveData.value = ResourceState.Loading()

        viewModelScope.launch(Dispatchers.IO) {
            try {
                editCardUseCase.execute(card)

                withContext(Dispatchers.Main) {
                    _editCardLiveData.value = ResourceState.Success(null)
                    _editCardLiveData.value = ResourceState.None()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    _editCardLiveData.value = ResourceState.Error(e.localizedMessage.orEmpty())
                    _editCardLiveData.value = ResourceState.None()
                }
            }
        }
    }

}