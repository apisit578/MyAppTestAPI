package com.example.myapptest1.data.dto

import com.example.myapptest1.domain.model.DogFact

fun DogFactDto.toDomain(): DogFact {
    return DogFact(
        id = id,
        fact = attributes.body
    )
}