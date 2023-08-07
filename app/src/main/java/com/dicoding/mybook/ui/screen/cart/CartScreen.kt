package com.dicoding.mybook.ui.screen.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.mybook.R
import com.dicoding.mybook.di.Injection
import com.dicoding.mybook.ui.ViewModelFactory
import com.dicoding.mybook.ui.common.UiState
import com.dicoding.mybook.ui.components.CartItem
import com.dicoding.mybook.ui.components.MainButton
import com.dicoding.mybook.ui.components.NoData
import com.dicoding.mybook.ui.components.textComponents.H3Text
import com.dicoding.mybook.ui.theme.darkBlue
import com.dicoding.mybook.ui.theme.darkPurple
import com.dicoding.mybook.ui.theme.white

@Composable
fun CartScreen(
    viewModel: CartViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    onOrderButtonClicked: (String) -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAddedOrderBooks()
            }
            is UiState.Success -> {
                val data = uiState.data
                if (data.orderBook.isNotEmpty()) {
                    CartContent(
                        data,
                        onProductCountChanged = { rewardId, count ->
                            viewModel.updateOrderBook(rewardId, count)
                        },
                        onOrderButtonClicked = onOrderButtonClicked,
                        modifier = Modifier
                            .padding(horizontal = 14.dp)
                            .padding(bottom = 16.dp)
                    )
                } else {
                    NoData(title = stringResource(R.string.no_data_cart))
                }
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun CartContent(
    state: CartState,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    onOrderButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val shareMessage = stringResource(
        R.string.share_message,
        state.orderBook.count(),
        state.totalRequiredPrice
    )
    Box(modifier = Modifier.fillMaxSize()) {
        val listState = rememberLazyListState()
        val showButton: Boolean by remember {
            derivedStateOf { listState.isScrollInProgress }
        }
        Column(
            modifier = modifier.fillMaxSize()
        ) {

            H3Text(
                description = stringResource(R.string.menu_cart),
                color = darkBlue,
                modifier = modifier.padding(top = 24.dp, bottom = 12.dp)
            )

            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(
                    start = 28.dp,
                    end = 28.dp,
                    top = 0.dp,
                    bottom = 120.dp
                ),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(state.orderBook, key = { it.book.id }) { item ->
                    CartItem(
                        orderId = item.book.id,
                        image = item.book.image,
                        title = item.book.title,
                        author = item.book.author,
                        totalPrice = item.book.price * item.count,
                        count = item.count,
                        onProductCountChanged = onProductCountChanged,
                    )
                    Divider(
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

            }

        }

        AnimatedVisibility(
            visible = state.orderBook.isNotEmpty() && !showButton,
            enter = fadeIn(),
            exit = fadeOut() + slideOutVertically(targetOffsetY = { 120 }),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .background(white)
                    .padding(32.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    H3Text(
                        description = stringResource(R.string.total_price),
                        color = darkBlue
                    )
                    H3Text(
                        description = stringResource(R.string.currency, state.totalRequiredPrice),
                        color = darkPurple,
                        textAlign = TextAlign.End,
                        modifier = Modifier.testTag("totalPrice")
                    )
                }
                MainButton(
                    text = stringResource(R.string.check_out),
                    onClick = { onOrderButtonClicked(shareMessage) },
                )
            }

        }
    }


}
