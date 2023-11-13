package com.example.beers.features.domain

import androidx.paging.PagingData
import com.example.beers.features.presentation.models.BeerUiModel
import kotlinx.coroutines.flow.Flow

interface BeerRepo {
    suspend fun getBeers(): Flow<PagingData<BeerUiModel>>
}