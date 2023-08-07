package com.dicoding.mybook.ui.components.textComponents

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

@Composable
fun H5Text(
    modifier: Modifier = Modifier,
    description: String,
    textAlign: TextAlign = TextAlign.Start,
    color: Color
) {
    Text(
        text = description,
        style = MaterialTheme.typography.h5.copy(
            color = color
        ),
        textAlign = textAlign,
        modifier = modifier
    )
}