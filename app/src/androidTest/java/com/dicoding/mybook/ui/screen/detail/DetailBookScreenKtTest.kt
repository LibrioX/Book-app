package com.dicoding.mybook.ui.screen.detail


import androidx.activity.ComponentActivity
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.dicoding.mybook.R
import com.dicoding.mybook.model.Book
import com.dicoding.mybook.model.OrderBook
import com.dicoding.mybook.onNodeWithStringId
import com.dicoding.mybook.ui.theme.MyBookTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailBookScreen {
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
        count = 0
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyBookTheme {
                ModalBottomSheet(
                    data = fakeOrderBook,
                    navigateBack = { },
                    navigateToCart = {},
                    initialTotalPoint = 0,
                    initialBottomValue = "Hidden",
                    initialSkipHalfValue = true
                )
            }
        }
    }

    //1. Memastikan data pada halaman detail tampil
    @Test
    fun detailContent_isDisplayed() {
        composeTestRule.onNodeWithTag("bookTitle").assertIsDisplayed()
        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(
                R.string.currency,
                fakeOrderBook.book.price
            )
        ).assertIsDisplayed()
    }

    //2. Memastikan modal bottom sheet tampil
    @Test
    fun modalBottomSheet_isDisplayed() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(
            R.string.add_to_cart
        )).assertIsNotDisplayed()
        composeTestRule.onNodeWithContentDescription("Modal Button").performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(
            R.string.add_to_cart
        )).assertIsDisplayed()
    }

    //3. Memastikan jumlah buku bertambah
    @Test
    fun increaseProduct_correctCounter() {
        composeTestRule.onNodeWithContentDescription("Modal Button").performClick()
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithTag("count").assert(hasText("2"))
    }

    //4. Initial value ketika modal terbuka adalah 1, maka akan dipastikan tertutup ketika melakukan decrease
    @Test
    fun decreaseProduct_closedModalBottomSheet() {
        composeTestRule.onNodeWithContentDescription("Modal Button").performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(
            R.string.add_to_cart
        )).assertIsDisplayed()
        composeTestRule.onNodeWithStringId(R.string.minus_symbol).performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(
            R.string.add_to_cart
        )).assertIsNotDisplayed()
    }

}