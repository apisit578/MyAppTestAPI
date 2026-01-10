package com.example.myapptest1.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapptest1.domain.usecase.GetDogFactUseCase
import com.example.myapptest1.presentation.stateUI.DogFactUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DogFactViewModel(
    private val getDogFactUseCase: GetDogFactUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<DogFactUiState>(DogFactUiState.Error(message = "no data found"))

    val uiState: StateFlow<DogFactUiState> = _uiState

    init {
        getDogFact()
    }

    fun getDogFact() {
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