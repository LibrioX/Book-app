package com.dicoding.mybook.ui.screen.profile

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.dicoding.mybook.ui.components.textComponents.H3Text
import com.dicoding.mybook.ui.components.textComponents.H4Text
import com.dicoding.mybook.ui.components.textComponents.Subtitle1Text
import com.dicoding.mybook.ui.theme.darkBlue
import com.dicoding.mybook.ui.theme.darkPurple
import com.dicoding.mybook.ui.theme.veryLightPurple
import com.dicoding.mybook.ui.theme.white

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 28.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        H3Text(
            description = stringResource(R.string.profile),
            color = darkBlue,
            modifier = modifier.padding(top = 24.dp, bottom = 12.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(white)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.foto_profile),
                contentDescription = stringResource(R.string.avatar),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .border(2.dp, darkPurple, CircleShape)
                    .padding(4.dp)
                    .clip(CircleShape)
                    .size(80.dp)
            )

            H3Text(
                description = stringResource(R.string.name),
                color = darkBlue,
                textAlign = TextAlign.Center,
                modifier = modifier.padding(top = 24.dp)
            )

            Subtitle1Text(
                description = stringResource(R.string.username),
                color = darkPurple
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp))
                .background(white)
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                H4Text(
                    description = stringResource(R.string.level),
                    color = darkBlue,
                )



                Subtitle1Text(
                    modifier = Modifier
                        .padding(top = 4.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(veryLightPurple)
                        .padding(vertical = 3.dp, horizontal = 12.dp),
                    description = stringResource(R.string.level_category),
                    color = darkBlue
                )

            }

            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {

                H4Text(
                    textAlign = TextAlign.End,
                    description = stringResource(R.string.level_score),
                    color = darkBlue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )

            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(veryLightPurple)

            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .clip(RoundedCornerShape(4.dp))
                        .height(8.dp)
                        .background(darkPurple)
                )
            }

        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.37f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(white)
                    .padding(12.dp),
            ) {

                H4Text(
                    description = stringResource(R.string.favorite),
                    color = darkBlue,
                )


                H4Text(
                    description = stringResource(R.string.see_all),
                    textAlign = TextAlign.End,
                    color = darkPurple,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Image(
                    painter = painterResource(R.drawable.book1),
                    contentDescription = stringResource(R.string.favorite_book),
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .fillMaxWidth()
                        .height(160.dp)
                )

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(white)
                    .padding(12.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {

                H4Text(
                    description = stringResource(R.string.info),
                    color = darkBlue,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                Column {

                    Subtitle1Text(
                        description = stringResource(R.string.time_reading),
                        color = darkPurple
                    )

                    H4Text(
                        description = stringResource(R.string.time_reading_value),
                        color = darkBlue,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                }

                Column {
                    Subtitle1Text(
                        description = stringResource(R.string.total_books),
                        color = darkPurple
                    )

                    H4Text(
                        description = stringResource(R.string.total_books_value),
                        color = darkBlue,
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProfilePreview() {
    ProfileScreen()
}