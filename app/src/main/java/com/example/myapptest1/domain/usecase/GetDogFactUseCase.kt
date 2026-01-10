package com.example.myapptest1.domain.usecase

import com.example.myapptest1.domain.model.DogFact

interface GetDogFactUseCase {
    suspend fun execute(): Result<DogFact>
}