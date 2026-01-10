package com.example.myapptest1.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapptest1.domain.usecase.GetDogFactUseCase
import com.example.myapptest1.presentation.viewmodel.DogFactViewModel

class DogFactViewModelFactory(
    private val getDogFactUseCase: GetDogFactUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogFactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DogFactViewModel(getDogFactUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}