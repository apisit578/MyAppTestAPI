package com.example.myapptest1.domain.usecase

import com.example.myapptest1.domain.model.CatModel
import com.example.myapptest1.domain.repository.CatRepository

class GetCatUseCaseImp(private val repository: CatRepository) : GetCatUseCase {
    override suspend fun execute(): Result<CatModel> {
        return repository.getCat()
    }
}