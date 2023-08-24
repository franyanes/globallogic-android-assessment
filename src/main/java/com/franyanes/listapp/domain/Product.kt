package com.franyanes.listapp.domain

import java.io.Serializable

data class Product(
    val title: String,
    val description: String,
    val imageUrl: String
) : Serializable // TODO: Volver a preguntar el por que de esto.
