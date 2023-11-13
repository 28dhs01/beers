package com.example.beers.features.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.beers.features.domain.BeerPagingSource
import com.example.beers.features.domain.BeerRepo
import com.example.beers.features.presentation.models.BeerUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeersUsecase @Inject constructor(
    private val beerRepo: BeerRepo
) {
    suspend fun getBeers(): Flow<PagingData<BeerUiModel>> {
        val beerFlow = Pager(config = PagingConfig(pageSize = 100)){
            BeerPagingSource(beerRepo = beerRepo)
        }.flow
        return beerFlow
    }
}