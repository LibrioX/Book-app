package com.dicoding.mybook.ui.components


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mybook.R
import com.dicoding.mybook.ui.components.textComponents.H3Text
import com.dicoding.mybook.ui.components.textComponents.Subtitle2Text
import com.dicoding.mybook.ui.theme.MyBookTheme
import com.dicoding.mybook.ui.theme.darkBlue
import com.dicoding.mybook.ui.theme.darkPurple
import com.dicoding.mybook.ui.theme.white

@Composable
fun DetailAppBar(
    modifier: Modifier = Modifier,
    title: String,
    author: String,
    navigateBack: () -> Unit
) {
    Column(
        modifier = modifier.padding(bottom = 40.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Icon(
                modifier = Modifier
                    .clickable { navigateBack() }
                    .drawBehind {
                        drawCircle(
                            color = white,
                            radius = 40f
                        )
                    }
                    .size(24.dp)
                    .align(Alignment.CenterStart),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(R.string.back),
                tint = darkPurple,
            )
            H3Text(
                description = title,
                color = darkBlue,
                modifier = Modifier
                    .align(Alignment.Center)
                    .testTag("bookTitle"),
            )
        }

        Subtitle2Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            description = author,
            color = darkPurple
        )
    }

}

@Composable
@Preview(showBackground = true)
fun DetailAppBarPreview() {
    MyBookTheme {
        Surface(modifier = Modifier.padding(16.dp)) {
            DetailAppBar(
                title = "Title",
                author = "Author",
                navigateBack = { }
            )
        }

    }
}