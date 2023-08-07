package com.dicoding.mybook.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.mybook.ui.components.textComponents.H4Text
import com.dicoding.mybook.ui.theme.MyBookTheme
import com.dicoding.mybook.ui.theme.veryLightPurple

@Composable
fun MainButton(
    modifier: Modifier = Modifier,
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        enabled = enabled,
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth()
            .height(40.dp)
            .semantics(mergeDescendants = true) {
                contentDescription = "Order Button"
            },
    ) {
        H4Text(
            description = text,
            color = veryLightPurple,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun MainButtonPreview() {
    MyBookTheme {
        MainButton(
            text = "Add to Cart",
            onClick = {}
        )
    }
}