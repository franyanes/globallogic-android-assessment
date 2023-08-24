package com.franyanes.listapp.data.repository

import com.franyanes.listapp.domain.Product
import com.franyanes.listapp.data.service.GlobalLogicService
import javax.inject.Inject

class ProductsRepository @Inject constructor(
    private val globalLogicService: GlobalLogicService
) {

    suspend fun getProducts(): List<Product> {
        // map: extension function
        return globalLogicService.getProductList().map {
            Product(
                title = it.title,
                description = it.description,
                imageUrl = it.image
            )
        }
    }
}
