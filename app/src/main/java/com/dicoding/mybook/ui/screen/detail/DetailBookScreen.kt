package com.dicoding.mybook.ui.screen.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.mybook.R
import com.dicoding.mybook.di.Injection
import com.dicoding.mybook.model.OrderBook
import com.dicoding.mybook.ui.ViewModelFactory
import com.dicoding.mybook.ui.common.UiState
import com.dicoding.mybook.ui.components.*
import kotlinx.coroutines.launch


@Composable
fun DetailBookScreen(
    rewardId: Long,
    viewModel: DetailBookViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getRewardById(rewardId)
            }
            is UiState.Success -> {
                val data = uiState.data
                ModalBottomSheet(
                    data = data,
                    navigateBack = navigateBack,
                    navigateToCart = {
                        viewModel.addToCart(data.book, it)
                        navigateToCart()
                    }
                )

            }
            is UiState.Error -> {}
        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalBottomSheet(
    data: OrderBook,
    navigateBack: () -> Unit,
    navigateToCart: (Int) -> Unit,
    initialTotalPoint: Int = 0,
    initialBottomValue: String = "Hidden",
    initialSkipHalfValue: Boolean = true
) {
    val scope = rememberCoroutineScope()
    var totalPoint by rememberSaveable { mutableStateOf(initialTotalPoint) }
    var orderCount by rememberSaveable { mutableStateOf(data.count) }
    val modalSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.valueOf(initialBottomValue),
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded },
        skipHalfExpanded = initialSkipHalfValue,
    )
    ModalBottomSheetLayout(
        sheetState = modalSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {

            totalPoint = data.book.price * orderCount

            ModalBottomSheetDesign(
                modifier = Modifier.padding(40.dp),
                orderId = data.book.id,
                title = data.book.title,
                author = data.book.author,
                count = orderCount,
                price = totalPoint,
                onProductIncreased = {
                    orderCount++
                },
                onProductDecreased = {
                    orderCount--
                    if (orderCount == 0) {
                        scope.launch { modalSheetState.hide() }
                    }
                },
                onAddToCartClick = {
                    navigateToCart(it)
                },
            )
        }
    ) {

        DetailBookContent(
            title = data.book.title,
            author = data.book.author,
            image = data.book.image,
            rating = data.book.rating,
            sell = data.book.sell,
            pages = data.book.pages,
            price = data.book.price,
            release = data.book.release,
            genre = data.book.genre,
            description = data.book.description,
            modifier = Modifier.padding(40.dp),
            navigateBack = navigateBack,
            navigateToCart = {
                scope.launch {
                    if (orderCount == 0) {
                        orderCount++
                    }
                    modalSheetState.animateTo(ModalBottomSheetValue.Expanded)
                }
            }
        )

    }
}

@Composable
fun DetailBookContent(
    title: String,
    author: String,
    image: Int,
    rating: Float,
    sell: String,
    pages: Int,
    price: Int,
    release: String,
    genre: String,
    description: String,
    navigateBack: () -> Unit,
    navigateToCart: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            DetailAppBar(
                title = title,
                author = author,
                navigateBack = navigateBack,
            )
            DetailContent(
                image = image,
                rating = rating,
                sell = sell,
                pages = pages,
                price = price,
                release = release,
                genre = genre,
                description = description,
            )

        }

        MainButton(
            text = stringResource(R.string.buy),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .semantics(mergeDescendants = true) {
                    contentDescription = "Modal Button"
                },
            onClick = navigateToCart

        )
    }

}
