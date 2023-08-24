package com.franyanes.listapp.presentation.productlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.franyanes.listapp.data.repository.ProductsRepository
import com.franyanes.listapp.domain.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {

/* Usando 'mutableStateOf' Jetpack Compose va a detectar automaticamente si hay algun cambio
    * en el valor de la variable y va a actualizar la UI. *//*

    var productList by mutableStateOf(fetchProductList())
        private set // Esto es para remarcar que nadie mas se tiene que hacer responsable de esto.
*/

    private val mutableProducts = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> = mutableProducts

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                mutableProducts.value = repository.getProducts()
            } catch (e: Exception) {
                // Handle error
                Log.e("ERROR EN EL FETCH", "asdasdasd", e)
            }
        }
    }
}
