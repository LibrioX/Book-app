package com.dicoding.mybook.ui.screen.cart

import com.dicoding.mybook.model.OrderBook

data class CartState(
    val orderBook: List<OrderBook>,
    val totalRequiredPrice: Int
)