package com.example.beers.di

import com.example.beers.BEERS_BASE_URL
import com.example.beers.features.data.BeerRepoImpl
import com.example.beers.features.data.remote.BeerApi
import com.example.beers.features.domain.BeerRepo
import com.example.beers.features.domain.usecases.GetBeersUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesBeersApi(): BeerApi {
        return Retrofit.Builder().baseUrl(BEERS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BeerApi::class.java)
    }
    @Provides
    @Singleton
    fun providesBeerRepo(beerApi: BeerApi): BeerRepo {
        return BeerRepoImpl(beerApi = beerApi)
    }

    @Singleton
    @Provides
    fun providesGetBeerUsecase(beerRepo:BeerRepo) : GetBeersUsecase{
        return GetBeersUsecase(beerRepo = beerRepo)
    }
}