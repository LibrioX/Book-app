package com.dicoding.mybook.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.mybook.data.BookRepository
import com.dicoding.mybook.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: BookRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderBooks() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderBooks()
                .collect { orderBook ->
                    val totalRequiredPoint =
                        orderBook.sumOf { it.book.price * it.count }
                    _uiState.value = UiState.Success(CartState(orderBook, totalRequiredPoint))
                }
        }
    }

    fun updateOrderBook(bookId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderBook(bookId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderBooks()
                    }
                }
        }
    }
}