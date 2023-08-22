package com.franyanes.listapp.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.franyanes.listapp.productlist.Product
import com.franyanes.listapp.ui.theme.ListAppTheme

class ProductDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                // TODO: Ver que onda el Theme.
                ListAppTheme {
                    // Uso `BoxWithConstraints` porque segun la docu sirve para la version landscape
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        ProductDetail(requireProductArgument())
                    }
                }
            }
        }
    }

    @Suppress("DEPRECATION") // la funcion que hay que usar requiere API 33 minimo
    private fun requireProductArgument() =
        requireArguments().getSerializable(productArgKey) as Product

    companion object {

        private const val productArgKey = "product_detail"

        fun newInstance(product: Product): ProductDetailFragment {
            val myFragment = ProductDetailFragment()
            val args = Bundle()
            args.putSerializable(productArgKey, product)
            myFragment.arguments = args
            return myFragment
        }
    }
}

@Composable
private fun ProductDetail(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(4f / 3f)
                .background(Color.Red)
        )
        Text(text = product.title)
        Text(text = product.description)
    }
}

private val previewProduct = Product("titulo 1", "descrp 1", "https://picsum.photos/100/100?image=1")