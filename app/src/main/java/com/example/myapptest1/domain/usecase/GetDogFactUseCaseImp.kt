package com.example.myapptest1.domain.usecase

import com.example.myapptest1.domain.model.DogFact
import com.example.myapptest1.domain.repository.DogFactRepository

class GetDogFactUseCaseImp(private val repository: DogFactRepository): GetDogFactUseCase {
    override suspend fun execute(): Result<DogFact> {
        // custom logic can be here as well
        return repository.getDogFact()
    }
}