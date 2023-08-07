package com.dicoding.mybook.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mybook.R
import com.dicoding.mybook.ui.components.textComponents.H4Text
import com.dicoding.mybook.ui.components.textComponents.H5Text
import com.dicoding.mybook.ui.components.textComponents.Subtitle2Text
import com.dicoding.mybook.ui.theme.MyBookTheme
import com.dicoding.mybook.ui.theme.darkBlue
import com.dicoding.mybook.ui.theme.darkPurple


@Composable
fun CartItem(
    orderId: Long,
    image: Int,
    title: String,
    author: String,
    count: Int,
    totalPrice: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.height(120.dp)
    ) {

        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .width(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 20.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {

                    H4Text(
                        description = title,
                        color = darkBlue
                    )

                    Subtitle2Text(
                        modifier = Modifier.padding(bottom = 4.dp),
                        description = author,
                        color = darkPurple
                    )

                }

                Icon(
                    Icons.Filled.Delete,
                    contentDescription = null,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable {
                            onProductCountChanged(orderId, 0)
                        },
                    tint = darkPurple
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                H5Text(
                    description = stringResource(R.string.currency, totalPrice),
                    color = darkPurple
                )
                ProductCounter(
                    orderId = orderId,
                    orderCount = count,
                    onProductIncreased = { onProductCountChanged(orderId, count + 1) },
                    onProductDecreased = { onProductCountChanged(orderId, count - 1) },
                )
            }
        }

    }
}


@Composable
@Preview(showBackground = true)
fun CartItemPreview() {
    MyBookTheme {
        CartItem(
            orderId = 1,
            image = R.drawable.book1,
            title = "The Alchemist",
            author = "Paulo Coelho",
            count = 1,
            totalPrice = 100000,
            onProductCountChanged = { _, _ -> }
        )
    }
}