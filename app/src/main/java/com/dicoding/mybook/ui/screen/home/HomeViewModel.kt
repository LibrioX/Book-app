package com.dicoding.mybook.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mybook.data.BookRepository
import com.dicoding.mybook.model.OrderBook
import com.dicoding.mybook.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: BookRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderBook>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderBook>>>
        get() = _uiState

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    private val _indexChip = mutableStateOf(0)
    val indexChip: State<Int> get() = _indexChip

    fun getAllBooks() {
        viewModelScope.launch {
            repository.getAllBooks()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }

    fun searchBooks(query: String) {
        _query.value = query
        viewModelScope.launch {
            repository.searchBooks(query)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }

    fun filterBooks(query: String) {
        viewModelScope.launch {
            if (query == "All")
                getAllBooks()
            else {
                repository.getBooksByGenre(query)
                    .catch {
                        _uiState.value = UiState.Error(it.message.toString())
                    }
                    .collect { orderRewards ->
                        _uiState.value = UiState.Success(orderRewards)
                    }
            }

        }
    }

    fun setIndexChip(index: Int) {
        _indexChip.value = index
    }
}