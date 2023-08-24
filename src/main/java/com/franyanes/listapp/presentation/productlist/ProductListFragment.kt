package com.franyanes.listapp.presentation.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.franyanes.listapp.R
import com.franyanes.listapp.domain.Product
import com.franyanes.listapp.presentation.productdetail.ProductDetailFragment
import com.franyanes.listapp.presentation.CoilImage
import com.franyanes.listapp.presentation.theme.ListAppTheme

class ProductListFragment : Fragment() {

    /*
    * Se podria crear esta variable dentro de un Composable, pero hay que importar una dependencia
    * y demas. */
    private val productListViewModel by viewModels<ProductListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ListAppTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colors.background
                    ) {
                        productListViewModel.fetchProducts()
                        val products =
                            productListViewModel.products.observeAsState().value ?: return@Surface
                        ProductList(products, ::navigateToProductDetail)
                    }
                }
            }
        }
    }

    private fun navigateToProductDetail(product: Product) {
        parentFragmentManager.commit {
            setReorderingAllowed(true)
            addToBackStack(null) // para que se agregue al backstack y poder ir para atras
            replace(R.id.root, ProductDetailFragment.newInstance(product))
        }
    }
}

@Composable
private fun ProductList(products: List<Product>, onProductClick: (Product) -> Unit) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 6.dp, vertical = 6.dp)
    ) {
        items(products.size) { index ->
            ProductItem(products[index], onProductClick)
        }
    }
}

@Composable
private fun ProductItem(product: Product, onClick: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 6.dp)
            .fillMaxWidth(),
        elevation = 2.dp, // sombreado
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(ROUNDED_CORNER_DP))
    ) {
        Row(
            modifier = Modifier.clickable {
                onClick(product)
            }
        ) {
            CoilImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(ROUNDED_CORNER_DP)),
                product = product
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = 6.dp,
                        horizontal = 10.dp
                    )
                    .fillMaxWidth()
                    .align(Alignment.Top)
            ) {
                Text(text = product.title)
                Text(text = product.description, maxLines = 2)
            }
        }
    }
}

private val ROUNDED_CORNER_DP = 10.dp

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    val productListViewModel = ProductListViewModel()
    ListAppTheme {
        val products = productListViewModel.products.observeAsState().value ?: return@ListAppTheme
        ProductList(products, {})
    }
}