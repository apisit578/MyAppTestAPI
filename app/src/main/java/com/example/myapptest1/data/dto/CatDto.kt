package com.example.myapptest1.data.dto

import com.example.myapptest1.domain.model.CatModel

fun CatResponseDto.toDomain(): CatModel {
    return CatModel(
        id = id,
        url = url,
    )
}