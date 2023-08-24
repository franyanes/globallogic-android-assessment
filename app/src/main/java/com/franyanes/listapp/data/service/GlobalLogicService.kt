package com.franyanes.listapp.data.service

import com.franyanes.listapp.data.dto.ProductDto
import retrofit2.http.GET

interface GlobalLogicService {

    @GET("list")
    suspend fun getProductList(): List<ProductDto>
}
