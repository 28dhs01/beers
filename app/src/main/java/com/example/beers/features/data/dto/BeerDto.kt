package com.example.beers.features.data.dto

import com.google.gson.annotations.SerializedName

data class BeerDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("tagline")
    val tagLine: String
)