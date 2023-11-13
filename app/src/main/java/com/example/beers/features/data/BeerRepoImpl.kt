package com.example.beers.features.data

import android.util.Log
import com.example.beers.features.data.dto.BeerDto
import com.example.beers.features.data.remote.BeerApi
import com.example.beers.features.domain.BeerRepo
import com.example.beers.features.domain.mappers.toBeerUiModel
import com.example.beers.features.presentation.models.BeerUiModel
import kotlinx.coroutines.delay
import java.lang.Exception
import javax.inject.Inject

class BeerRepoImpl @Inject constructor(
    private val beerApi: BeerApi
) : BeerRepo{
    override suspend fun getBeers(page:Int,limit:Int): List<BeerUiModel> {
        try {
            delay(2000)
            val response = beerApi.getBeers(page = page, limit = limit)
            if( response.isSuccessful ){
                val data = response.body()?.map { it->
                    it.toBeerUiModel()
                } ?: emptyList()
                return data
            }
        }catch (e: Exception){
            Log.e("error","${e.message}")
        }
        return emptyList()
    }
}