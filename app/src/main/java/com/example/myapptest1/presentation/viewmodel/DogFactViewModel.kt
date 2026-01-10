package com.example.myapptest1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapptest1.domain.model.CatModel
import com.example.myapptest1.domain.usecase.GetCatUseCase
import com.example.myapptest1.domain.usecase.GetDogFactUseCase
import com.example.myapptest1.presentation.stateUI.CatUiState
import com.example.myapptest1.presentation.stateUI.DogFactUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogFactViewModel(
    private val getDogFactUseCase: GetDogFactUseCase,
    private val getCatUseCase: GetCatUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<DogFactUiState>(DogFactUiState.Error(message = "no data found"))
    val uiState: StateFlow<DogFactUiState> = _uiState

    private val _uiCatState =
        MutableStateFlow<CatUiState>(CatUiState.Error(message = "cat no have"))
    val uiCatState: StateFlow<CatUiState> = _uiCatState

    init {
        getDogFact()
        getCat()
    }

    private fun getCat() {
        viewModelScope.launch {
            getCatUseCase.execute()
                .onSuccess { data ->
                    _uiCatState.value = CatUiState.Success(cat = data)
                }
                .onFailure { data ->
                    _uiCatState.value = CatUiState.Error(message = data.message ?: "no data")
                }
        }
    }

    private fun getDogFact() {
        viewModelScope.launch {
            getDogFactUseCase.execute()
                .onSuccess { data ->
                    _uiState.value = DogFactUiState.Success(fact = data)
                }
                .onFailure { data ->
                    _uiState.value = DogFactUiState.Error(message = data.message ?: "no data found")
                }
        }

    }
}