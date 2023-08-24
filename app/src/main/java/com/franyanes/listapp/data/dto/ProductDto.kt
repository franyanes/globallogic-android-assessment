package com.franyanes.listapp.data.dto

import com.google.gson.annotations.SerializedName

data class ProductDto(
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("title")
    val title: String
)