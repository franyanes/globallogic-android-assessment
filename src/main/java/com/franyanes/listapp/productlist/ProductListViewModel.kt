package com.franyanes.listapp.productlist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel

class ProductListViewModel : ViewModel() {

    /* Usando 'mutableStateOf' Jetpack Compose va a detectar automaticamente si hay algun cambio
    * en el valor de la variable y va a actualizar la UI. */
    var productList by mutableStateOf(fetchProductList())
        private set // Esto es para remarcar que nadie mas se tiene que hacer responsable de esto.

    fun fetchProductList(): List<Product> {
        return previewProductsList
    }
}

private val previewProductsList = listOf<Product>(
    Product("titulo 1", "descrp 1", "https://picsum.photos/100/100?image=1"),
    Product("titulo 2", "descrp 2", "https://picsum.photos/100/100?image=2"),
    Product("titulo 3", "descrp 3", "https://picsum.photos/100/100?image=3"),
    Product("titulo 4", "anduvo el viewmodel", "https://picsum.photos/100/100?image=4"),
)
