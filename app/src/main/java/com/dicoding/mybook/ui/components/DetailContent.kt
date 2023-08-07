package com.dicoding.mybook.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mybook.R
import com.dicoding.mybook.ui.components.textComponents.H3Text
import com.dicoding.mybook.ui.components.textComponents.H6Text
import com.dicoding.mybook.ui.components.textComponents.Subtitle1Text
import com.dicoding.mybook.ui.components.textComponents.Subtitle2Text
import com.dicoding.mybook.ui.theme.*

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    image: Int,
    rating: Float,
    sell: String,
    pages: Int,
    price: Int,
    release: String,
    genre: String,
    description: String,
) {
    Column(modifier = modifier.padding(bottom = 40.dp)) {
        Row(
            modifier = Modifier.height(240.dp)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(160.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column(
                modifier = Modifier
                    .padding(start = 40.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .width(60.dp)
                    .fillMaxHeight()
                    .background(white)
                    .padding(vertical = 12.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CardItem(
                    icon = Icons.Filled.Star,
                    title = stringResource(R.string.rating),
                    value = rating.toString(),
                    color = yellow
                )
                CardItem(
                    icon = Icons.Filled.Person,
                    title = stringResource(R.string.sell),
                    value = sell,
                    color = veryLightPurple
                )
                CardItem(
                    icon = Icons.Filled.Book,
                    title = stringResource(R.string.pages),
                    value = pages.toString(),
                    color = veryLightPurple
                )
            }


        }
        DescriptionContent(
            price = price,
            release = release,
            genre = genre,
            description = description,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    value: String,
    color: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Subtitle1Text(
            description = title,
            color = darkPurple,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(12.dp),
                tint = color
            )
            Subtitle2Text(
                description = value,
                color = darkBlue
            )
        }

    }
}

@Composable
fun DescriptionContent(
    modifier: Modifier = Modifier,
    price: Int,
    release: String,
    genre: String,
    description: String
) {
    Column(modifier = modifier) {

        H3Text(
            description = stringResource(R.string.currency, price),
            color = darkPurple,
            modifier = Modifier
                .padding(top = 12.dp),
        )

        Subtitle1Text(
            description = stringResource(R.string.release, release),
            color = darkPurple,
        )


        Subtitle1Text(
            description = genre,
            color = darkBlue,
            modifier = Modifier
                .padding(top = 4.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(veryLightPurple)
                .padding(vertical = 3.dp, horizontal = 12.dp)
        )

        H3Text(
            description = stringResource(R.string.synopsis),
            color = darkBlue,
            modifier = Modifier
                .padding(top = 12.dp),
        )

        H6Text(description = description, color = darkPurple)


    }
}

@Composable
@Preview(showBackground = true)
fun DetailContentPreview() {
    MyBookTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            DetailContent(
                image = R.drawable.book1,
                rating = 4.7F,
                sell = "100k",
                pages = 220,
                price = 200000,
                release = "26 November 2022",
                genre = "Action",
                description = "lorem ipsum dolor sit amet "
            )
        }

    }
}