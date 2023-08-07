package com.dicoding.mybook

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.dicoding.mybook.model.FakeBookDataSource
import com.dicoding.mybook.ui.navigation.Screen
import com.dicoding.mybook.ui.theme.MyBookTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BookAppKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MyBookTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                BookApp(navController = navController)
            }
        }
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesToDetailWithData() {
        composeTestRule.onNodeWithTag("BookList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeBookDataSource.dummyBooks[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailReward.route)
        composeTestRule.onNodeWithTag("bookTitle")
            .assert(hasText(FakeBookDataSource.dummyBooks[10].title))
    }

    @Test
    fun navHost_bottomNavigation_working() {
        composeTestRule.onNodeWithStringId(R.string.menu_cart).performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_profile).performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_clickItem_navigatesBack() {
        composeTestRule.onNodeWithTag("BookList").performScrollToIndex(10)
        composeTestRule.onNodeWithText(FakeBookDataSource.dummyBooks[10].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailReward.route)
        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.back)).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_checkout_rightBackStack() {
        composeTestRule.onNodeWithText(FakeBookDataSource.dummyBooks[1].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailReward.route)
        composeTestRule.onNodeWithContentDescription("Modal Button").performClick()
        composeTestRule.onNodeWithStringId(R.string.plus_symbol).performClick()
        composeTestRule.onNodeWithContentDescription("Order Button").performClick()
        navController.assertCurrentRouteName(Screen.Cart.route)
        composeTestRule.onNodeWithStringId(R.string.menu_home).performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }
}