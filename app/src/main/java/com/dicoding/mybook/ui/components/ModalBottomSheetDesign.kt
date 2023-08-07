package com.dicoding.mybook.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mybook.R
import com.dicoding.mybook.ui.components.textComponents.H3Text
import com.dicoding.mybook.ui.components.textComponents.Subtitle2Text
import com.dicoding.mybook.ui.theme.darkBlue
import com.dicoding.mybook.ui.theme.darkPurple

@Composable
fun ModalBottomSheetDesign(
    modifier: Modifier = Modifier,
    orderId: Long,
    title: String,
    author: String,
    price: Int,
    count: Int = 0,
    enabled: Boolean = true,
    onAddToCartClick: (Int) -> Unit,
    onProductIncreased: (Long) -> Unit,
    onProductDecreased: (Long) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.width(150.dp)
            ) {
                H3Text(
                    description = title,
                    color = darkBlue,
                )
                Subtitle2Text(
                    description = author,
                    color = darkPurple
                )
            }

            H3Text(
                description = stringResource(R.string.currency, price),
                color = darkPurple,
                modifier = Modifier.width(200.dp),
                textAlign = TextAlign.End
            )
        }

        ProductCounter(
            orderId = orderId,
            orderCount = count,
            onProductIncreased = onProductIncreased,
            onProductDecreased = onProductDecreased
        )

        MainButton(
            text = stringResource(R.string.add_to_cart),
            onClick = { onAddToCartClick(count) },
            enabled = enabled
        )


    }

}

@Composable
@Preview(showBackground = true)
fun ModalBottomSheetDesignPreview() {
    ModalBottomSheetDesign(
        orderId = 1,
        title = "Title",
        author = "Author",
        price = 10000,
        count = 0,
        onAddToCartClick = {},
        onProductIncreased = {},
        onProductDecreased = {}
    )
}


