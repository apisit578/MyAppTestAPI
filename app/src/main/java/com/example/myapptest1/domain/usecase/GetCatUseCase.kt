package com.example.myapptest1.domain.usecase

import com.example.myapptest1.domain.model.CatModel

interface GetCatUseCase {
    suspend fun execute(): Result<CatModel>
}