package com.example.composepagging3.data.mappers

import com.example.composepagging3.data.local.BeerEntity
import com.example.composepagging3.domain.BeerDto


fun BeerDto.toBeerEntity() : BeerEntity{
    return BeerEntity(
        id = id,
        name = name,
        tagline = tagline,
        description = description,
        firstBrewed = first_brewed,
        imageUrl = image_url
    )
}

fun