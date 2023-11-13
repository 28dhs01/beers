package com.example.beers.features.domain.mappers

import com.example.beers.features.data.dto.BeerDto
import com.example.beers.features.presentation.models.BeerUiModel

fun BeerDto.toBeerUiModel(): BeerUiModel {
    return BeerUiModel(
        id = this.id,
        tagLine = this.tagLine
    )
}