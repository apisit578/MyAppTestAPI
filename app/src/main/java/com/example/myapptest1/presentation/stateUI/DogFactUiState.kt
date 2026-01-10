package com.example.myapptest1.presentation.stateUI

import com.example.myapptest1.domain.model.DogFact

sealed class DogFactUiState {
//    object Loading : DogFactUiState()
    data class Success(val fact: DogFact) : DogFactUiState()
    data class Error(val message: String) : DogFactUiState()
}