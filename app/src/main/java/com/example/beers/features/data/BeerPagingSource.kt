package com.example.beers.features.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.beers.PAGE_LIMIT
import com.example.beers.features.data.remote.BeerApi
import com.example.beers.features.domain.mappers.toBeerUiModel
import com.example.beers.features.presentation.models.BeerUiModel
import kotlinx.coroutines.delay
import javax.inject.Inject

class BeerPagingSource @Inject constructor(
    private val beerApi: BeerApi
) : PagingSource<Int, BeerUiModel>() {
    override fun getRefreshKey(state: PagingState<Int, BeerUiModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BeerUiModel> {
        try {
            // Start refresh at page 1 if undefined.
            val pageToLoad = params.key ?: 1
            delay(2000)
            val dtoResponse = beerApi.getBeers(page = pageToLoad, limit = PAGE_LIMIT).body()
            val modelResponse = dtoResponse?.map { it.toBeerUiModel() }
            return LoadResult.Page(
                data = modelResponse!!,
                prevKey = null, // Only paging forward.
                nextKey = if(pageToLoad<=32) pageToLoad.plus(1) else null
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error for
            // expected errors (such as a network failure).
            return LoadResult.Error(throwable = e)
        }
    }

}