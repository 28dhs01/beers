package com.example.beers.features.presentation.contract

import androidx.paging.PagingData
import com.example.beers.features.presentation.models.BeerUiModel
import kotlinx.coroutines.flow.Flow

data class BeerUiState(
    val beersList: Flow<PagingData<BeerUiModel>>? = null
)
