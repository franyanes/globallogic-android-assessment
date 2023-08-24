package com.franyanes.listapp.presentation.productlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franyanes.listapp.data.repository.ProductsRepository
import com.franyanes.listapp.domain.Product
import kotlinx.coroutines.launch

class ProductListViewModel : ViewModel() {

/* Usando 'mutableStateOf' Jetpack Compose va a detectar automaticamente si hay algun cambio
    * en el valor de la variable y va a actualizar la UI. *//*

    var productList by mutableStateOf(fetchProductList())
        private set // Esto es para remarcar que nadie mas se tiene que hacer responsable de esto.
*/

    private val repository = ProductsRepository()
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = _products

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                _products.value = repository.getProducts()
            } catch (e: Exception) {
                // Handle error
                Log.e("ERROR EN EL FETCH", "asdasdasd", e)
            }
        }
//        return previewProductsList
    }
}

private val previewProductsList = listOf<Product>(
    Product("titulo 1", "descrp 1", "https://picsum.photos/100/100?image=1"),
    Product("titulo 2", "descrp 2", "https://picsum.photos/100/100?image=2"),
    Product("titulo 3", "descrp 3", "https://picsum.photos/100/100?image=3"),
    Product("titulo 4", "anduvo el viewmodel", "https://picsum.photos/100/100?image=4"),
)
