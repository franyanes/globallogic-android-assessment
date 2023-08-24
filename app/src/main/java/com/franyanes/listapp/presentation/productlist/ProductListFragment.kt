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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import com.franyanes.listapp.R
import com.franyanes.listapp.domain.Product
import com.franyanes.listapp.presentation.productdetail.ProductDetailFragment
import com.franyanes.listapp.presentation.CoilImage
import com.franyanes.listapp.presentation.theme.ListAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
            .fillMaxWidth()
            .height(100.dp),
        elevation = 2.dp, // sombreado
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(ROUNDED_CORNER_DP))
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onClick(product) }
        ) {
            CoilImage(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize()
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
                Text(
                    text = product.title,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                )
                Text(
                    text = product.description,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp
                    )
                )
            }
        }
    }
}

private val ROUNDED_CORNER_DP = 10.dp

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    ListAppTheme {
        ProductList(previewProductList, {})
    }
}

private val previewProductList = listOf<Product>(
    Product("titulo 1", "descrp 1", "https://picsum.photos/100/100?image=1"),
    Product("titulo 2", "descrp 2", "https://picsum.photos/100/100?image=2"),
    Product("titulo 3", "descrp 3", "https://picsum.photos/100/100?image=3"),
    Product("titulo 4", "anduvo el viewmodel", "https://picsum.photos/100/100?image=4"),
)
