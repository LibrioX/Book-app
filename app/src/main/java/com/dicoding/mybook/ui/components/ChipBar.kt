package com.dicoding.mybook.ui.components


import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dicoding.mybook.ui.components.textComponents.H5Text
import com.dicoding.mybook.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipBar(
    modifier: Modifier = Modifier,
    index: Int,
    text: String,
    isSelected: Boolean = false,
    onClickChip: (String, Int) -> Unit,
) {
    Chip(
        onClick = { onClickChip(text, index) },
        colors = ChipDefaults.chipColors(if (isSelected) darkPurple else white),
        modifier = modifier
    ) {
        H5Text(
            description = text,
            color = if (isSelected) veryLightPurple else darkBlue
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ChipBarPreview() {
    MyBookTheme {
        ChipBar(index = 0, text = "All", onClickChip = { _, _ ->
        })
    }
}


