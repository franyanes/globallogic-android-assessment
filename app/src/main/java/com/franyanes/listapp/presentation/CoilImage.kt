package com.franyanes.listapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.franyanes.listapp.domain.Product

@Composable
fun CoilImage(modifier: Modifier = Modifier, product: Product) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(product.imageUrl)
            .crossfade(true)
            .build(),
        contentDescription = "sarasa", // TODO: revisar por que necesita esto
        modifier = modifier
    )
}

/*        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(ROUNDED_CORNER_DP))*/