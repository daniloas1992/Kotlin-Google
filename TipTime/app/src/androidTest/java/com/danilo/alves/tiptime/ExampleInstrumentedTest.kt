package com.danilo.alves.tiptime

import android.content.res.Resources
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.danilo.alves.tiptime.ui.theme.TipTimeTheme
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.text.NumberFormat


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    var res: Resources = getInstrumentation().getTargetContext().getResources()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.danilo.alves.tiptime", appContext.packageName)
    }

    @Test
    fun calculate_20_percent_tip() {
        composeTestRule.setContent {
            TipTimeTheme {
                TipTimeLayout()
            }
        }

        composeTestRule.onNodeWithText(res.getString(R.string.bill_amount)).performTextInput("10")

        composeTestRule.onNodeWithText(res.getString(R.string.how_was_the_service)).performTextInput("20")

        val expectedTip = NumberFormat.getCurrencyInstance().format(2)
//res.getString(R.string.tip_amount, expectedTip)
        //composeTestRule.onNodeWithText("Tip Amount: $expectedTip").assertExists("No node with this text was found.")

    }
}