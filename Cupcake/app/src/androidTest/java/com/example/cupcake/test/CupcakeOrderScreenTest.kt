package com.example.cupcake.test

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.cupcake.CupcakeApp
import com.example.cupcake.ui.SelectOptionScreen
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.data.OrderUiState
import com.example.cupcake.ui.OrderSummaryScreen
import com.example.cupcake.ui.StartOrderScreen
import com.example.cupcake.ui.components.FormattedPriceLabel
import org.junit.Rule
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CupcakeOrderScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun selectOptionScreen_verifyContent() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        flavors.forEach {
            flavor -> composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        }

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, subtotal)
        ).assertIsDisplayed()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()
    }

    @Test
    fun startScreen_verifyContent() {

        composeTestRule.setContent {
            StartOrderScreen( quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = {}
            )
        }

        DataSource.quantityOptions.forEach {
            composeTestRule.onNodeWithStringId(it.first).assertIsDisplayed()
        }
    }

    @Test
    fun summaryScreen_verifyContent() {

        val quantity = 1

        val numberOfCupcakes = composeTestRule.activity.resources.getQuantityString(
            R.plurals.cupcakes,
            quantity,
            quantity
        )

        val flavor = "Coffee"

        val pickup = getFormattedDate()

        val subtotal = "$100.00"

        composeTestRule.setContent {
            OrderSummaryScreen(
                orderUiState = OrderUiState(quantity, flavor, pickup, subtotal),
                onSendButtonClicked = {subject: String, summary: String ->},
                onCancelButtonClicked = {}
            )
        }

        composeTestRule.onNodeWithText(numberOfCupcakes).assertIsDisplayed()
        composeTestRule.onNodeWithText(flavor).assertIsDisplayed()
        composeTestRule.onNodeWithText(pickup).assertIsDisplayed()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.subtotal_price, subtotal)
        ).assertIsDisplayed()

    }

    private fun getFormattedDate(): String {
        val calendar = Calendar.getInstance()
        calendar.add(java.util.Calendar.DATE, 1)
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    @Test
    fun selectOptionScreen_verifyNextIsEnabled() {
        val flavors = listOf("Vanilla", "Chocolate", "Hazelnut", "Cookie", "Mango")
        val subtotal = "$100"

        composeTestRule.setContent {
            SelectOptionScreen(subtotal = subtotal, options = flavors)
        }

        composeTestRule.onNodeWithStringId(R.string.next).assertIsNotEnabled()

        composeTestRule.onNodeWithText(
            composeTestRule.activity.getString(R.string.vanilla)
        ).performClick()

        composeTestRule.onNodeWithStringId(R.string.next).assertIsEnabled()
    }
}