package com.example.beers.features.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.beers.features.data.remote.BeerApi
import com.example.beers.features.domain.BeerRepo
import com.example.beers.features.presentation.models.BeerUiModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BeerRepoImpl @Inject constructor(
    private val beerApi: BeerApi
) : BeerRepo{
    override suspend fun getBeers(): Flow<PagingData<BeerUiModel>> {
        val pagingDataFlow = Pager(config = PagingConfig(pageSize = 50)){
            BeerPagingSource(beerApi = beerApi)
        }.flow
        return pagingDataFlow
    }
}