package com.dicoding.mybook.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dicoding.mybook.R
import com.dicoding.mybook.di.Injection
import com.dicoding.mybook.model.GenreDataSource
import com.dicoding.mybook.model.OrderBook
import com.dicoding.mybook.ui.ViewModelFactory
import com.dicoding.mybook.ui.common.UiState
import com.dicoding.mybook.ui.components.BookItem
import com.dicoding.mybook.ui.components.ChipBar
import com.dicoding.mybook.ui.components.NoData
import com.dicoding.mybook.ui.components.SearchBar
import com.dicoding.mybook.ui.components.textComponents.H3Text
import com.dicoding.mybook.ui.theme.darkBlue


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val query by viewModel.query
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getAllBooks()
                viewModel.setIndexChip(0)
            }
            is UiState.Success -> {
                val chips = GenreDataSource.chips
                HomeContent(
                    modifier = modifier,
                    data = uiState.data,
                    dataGenre = chips,
                    query = query,
                    viewModel = viewModel,
                    navigateToDetail = navigateToDetail,
                    onQueryChange = {
                        viewModel.searchBooks(it)
                        viewModel.setIndexChip(0)
                    },
                    onClickChip = { it, index ->
                        viewModel.filterBooks(it)
                        viewModel.setIndexChip(index)
                    }
                )

            }
            is UiState.Error -> {}
        }
    }


}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    data: List<OrderBook>,
    dataGenre: List<String>,
    query: String = "",
    viewModel: HomeViewModel,
    navigateToDetail: (Long) -> Unit,
    onQueryChange: (String) -> Unit,
    onClickChip: (String, Int) -> Unit,
) {
    Column(modifier = Modifier.padding(horizontal = 28.dp)) {
        H3Text(
            description = stringResource(R.string.welcome_word),
            color = darkBlue,
            modifier = modifier.padding(top = 24.dp, bottom = 12.dp)
        )
        SearchBar(
            modifier.padding(bottom = 12.dp),
            query = query,
            onQueryChange = onQueryChange
        )

        LazyRow(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            val selectedChip by viewModel.indexChip

            itemsIndexed(dataGenre) { index, chip ->
                ChipBar(
                    index = index,
                    text = chip,
                    isSelected = selectedChip == index,
                    onClickChip = onClickChip
                )
            }

        }

        if (data.isEmpty()) {
            NoData(title = stringResource(R.string.no_data))
        } else {
            LazyColumn(
                modifier = modifier.testTag("BookList"),
            ) {
                items(data, key = { it.book.id }) { data ->
                    BookItem(
                        image = data.book.image,
                        title = data.book.title,
                        author = data.book.author,
                        price = data.book.price,
                        genre = data.book.genre,
                        rating = data.book.rating,
                        navigateToDetail = {
                            navigateToDetail(data.book.id)
                        },
                        modifier = Modifier
                            .padding(10.dp)
                    )
                }
            }
        }


    }
}


