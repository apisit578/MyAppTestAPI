package com.example.myapptest1.domain.repository

import com.example.myapptest1.domain.model.CatModel

interface CatRepository {
    suspend fun getCat(): Result<CatModel>
}