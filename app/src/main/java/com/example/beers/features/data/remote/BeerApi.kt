package com.example.beers.features.data.remote

import com.example.beers.features.data.dto.BeerDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BeerApi {
    @GET("v2/beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") limit: Int
    ): Response<List<BeerDto>>
}