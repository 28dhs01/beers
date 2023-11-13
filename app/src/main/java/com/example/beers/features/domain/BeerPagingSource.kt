package com.example.beers.features.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.beers.PAGE_LIMIT
import com.example.beers.features.presentation.models.BeerUiModel
import javax.inject.Inject

class BeerPagingSource @Inject constructor(
    private val beerRepo: BeerRepo
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
            val response = beerRepo.getBeers(page = pageToLoad, limit = PAGE_LIMIT)
            return LoadResult.Page(
                data = response,
                prevKey = null, // Only paging forward.
                nextKey = pageToLoad.plus(1)
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error for
            // expected errors (such as a network failure).
            return LoadResult.Error(throwable = e)
        }
    }

}