package com.dicoding.mybook.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mybook.R
import com.dicoding.mybook.ui.components.textComponents.H3Text
import com.dicoding.mybook.ui.theme.darkBlue

@Composable
fun NoData(
    modifier: Modifier = Modifier,
    title: String
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painterResource(id = R.drawable.no_data_book),
                contentDescription = title
            )
            H3Text(
                description = title,
                color = darkBlue,
            )
        }

    }
}

@Composable
@Preview(showBackground = true)
fun NoDataPreview() {
    NoData(
        title = stringResource(id = R.string.no_data)
    )
}