package com.dicoding.mybook.ui.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mybook.R
import com.dicoding.mybook.ui.components.textComponents.H4Text
import com.dicoding.mybook.ui.components.textComponents.H5Text
import com.dicoding.mybook.ui.components.textComponents.Subtitle1Text
import com.dicoding.mybook.ui.components.textComponents.Subtitle2Text
import com.dicoding.mybook.ui.theme.*


@Composable
fun BookItem(
    image: Int,
    title: String,
    author: String,
    price: Int,
    genre: String,
    rating: Float,
    navigateToDetail: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = 2.dp,
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                navigateToDetail()
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(10.dp)
        ) {
            Row {
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
                        .fillMaxSize()
                        .padding(start = 20.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {

                        H4Text(
                            description = title,
                            color = darkBlue
                        )
                        Subtitle2Text(
                            description = author,
                            color = darkPurple
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceAround,
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(gray)
                                .width(50.dp)
                                .padding(2.dp)

                        ) {
                            Icon(
                                Icons.Filled.Star,
                                contentDescription = null,
                                modifier = Modifier.size(12.dp),
                                tint = yellow
                            )

                            Subtitle2Text(
                                description = rating.toString(),
                                color = darkBlue,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        Subtitle1Text(
                            description = genre,
                            color = veryLightPurple,
                            modifier = Modifier
                                .padding(top = 4.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(darkPurple)
                                .padding(vertical = 3.dp, horizontal = 12.dp),
                        )
                    }

                    H5Text(
                        modifier = Modifier.fillMaxWidth(),
                        description = stringResource(R.string.currency, price),
                        textAlign = TextAlign.End,
                        color = darkPurple
                    )

                }
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun BookItemPreview() {
    MyBookTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            BookItem(
                image = R.drawable.book1,
                title = "Peony World",
                author = "Dandy Candra",
                price = 100000,
                genre = "Action",
                rating = 4.7f,
                navigateToDetail = {}
            )
        }

    }
}