package com.dicoding.mybook.data

import com.dicoding.mybook.model.FakeBookDataSource
import com.dicoding.mybook.model.OrderBook
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class BookRepository {

    private val orderBooks = mutableListOf<OrderBook>()

    init {
        if (orderBooks.isEmpty()) {
            FakeBookDataSource.dummyBooks.forEach {
                orderBooks.add(OrderBook(it, 0))
            }
        }
    }

    fun getAllBooks(): Flow<List<OrderBook>> {
            return flowOf(orderBooks)
    }


    fun getBooksByGenre(genre: String): Flow<List<OrderBook>> {
        return getAllBooks()
            .map { orderBooks ->
                orderBooks.filter { orderBook ->
                    orderBook.book.genre == genre
                }
            }
    }

    fun searchBooks(query: String): Flow<List<OrderBook>> {
        return getAllBooks()
            .map { orderBooks ->
                orderBooks.filter { orderBook ->
                    orderBook.book.title.contains(query, ignoreCase = true)
                }
            }
    }

    fun getOrderBookById(bookId: Long): OrderBook {
        return orderBooks.first {
            it.book.id == bookId
        }
    }

    fun updateOrderBook(bookId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderBooks.indexOfFirst { it.book.id == bookId }
        val result = if (index >= 0) {
            val orderBook = orderBooks[index]
            orderBooks[index] =
                orderBook.copy(book = orderBook.book, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderBooks(): Flow<List<OrderBook>> {
        return getAllBooks()
            .map { orderBooks ->
                orderBooks.filter { orderBook ->
                    orderBook.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: BookRepository? = null

        fun getInstance(): BookRepository =
            instance ?: synchronized(this) {
                BookRepository().apply {
                    instance = this
                }
            }
    }

}