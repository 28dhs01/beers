package com.example.beers.features.domain

import com.example.beers.features.data.dto.BeerDto
import com.example.beers.features.presentation.models.BeerUiModel

interface BeerRepo {
    suspend fun getBeers(page:Int, limit: Int): List<BeerUiModel>
}