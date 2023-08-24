package com.franyanes.listapp.presentation.productdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.franyanes.listapp.domain.Product
import com.franyanes.listapp.presentation.CoilImage
import com.franyanes.listapp.presentation.theme.ListAppTheme

class ProductDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ListAppTheme {
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
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CoilImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f),
            product = product
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {
            Text(
                text = product.title,
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp
                )
            )
            Divider(thickness = 0.5.dp, color = Color.Black)
            Text(text = product.description)
        }
    }
}
