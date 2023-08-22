package com.franyanes.listapp.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.*
import com.franyanes.listapp.R
import com.franyanes.listapp.productdetail.ProductDetailFragment
import com.franyanes.listapp.ui.theme.ListAppTheme

// TODO: es una constante porque se usa para la card y la foto, esta bien?
val ROUNDED_CORNER_DP = 10.dp

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
                        ProductList(productListViewModel.productList, ::navigateToProductDetail)
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
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 5.dp)
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
            .padding(horizontal = 5.dp, vertical = 5.dp)
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
//            Image(painter = Painter., contentDescription = )
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(ROUNDED_CORNER_DP))
                    .background(Color.Red)
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = 5.dp,
                        horizontal = 10.dp)
                    .fillMaxWidth()
                    .align(Alignment.Top)
            ) {
                Text(text = product.title)
                Text(text = product.description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    val productListViewModel = ProductListViewModel()
    ListAppTheme {
        ProductList(productListViewModel.productList, {})
    }
}
