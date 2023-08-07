package com.dicoding.mybook.ui.screen.cart

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.dicoding.mybook.R
import com.dicoding.mybook.model.Book
import com.dicoding.mybook.model.OrderBook
import com.dicoding.mybook.ui.theme.MyBookTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CartScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private val fakeOrderBook = OrderBook(
        book = Book(
            4,
            R.drawable.book4,
            "Filosofi Teras",
            "Henry Manampiring",
            "Ini Deskripsi",
            "Romance",
            4.5F,
            80000,
            200,
            "100k",
            "20 November 2022"
        ),
        count = 3
    )

    private val cartState = CartState(
        orderBook = listOf(fakeOrderBook),
        totalRequiredPrice = 240000
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyBookTheme {
                CartContent(state = cartState, onProductCountChanged = { _, _ ->

                }, onOrderButtonClicked = {})
            }
        }
    }

    //1. Memastikan data pada halaman cart tampil
    @Test
    fun item_isDisplayed() {
        composeTestRule.onNodeWithText("Filosofi Teras").assertIsDisplayed()
        composeTestRule.onNodeWithText(
            fakeOrderBook.count.toString()
        ).assertIsDisplayed()
    }

}