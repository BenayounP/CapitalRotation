package eu.pbenayoun.capitalrotation

import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import eu.pbenayoun.capitalrotation.presentation.MainActivity
import eu.pbenayoun.capitalrotation.util.ScreenRobot

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Before
import org.junit.Ignore
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MainActivityInstrumentedTest {

    val homeEditTextId = R.id.home_edit_search
    val homeButtonId = R.id.home_button
    val homeSnackBarId = com.google.android.material.R.id.snackbar_text
    val homeTitleId = R.id.home_title

    @Ignore
    fun typeAndClick(query: String) : ScreenRobot {
        return ScreenRobot.getInstance().enterTextIntoView(homeEditTextId,query)
            .clickOkOnView(homeButtonId)
    }

    @Ignore
    fun getString(stringId : Int) = InstrumentationRegistry.getInstrumentation().targetContext.getString(stringId)

    @get:Rule
    @JvmField
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setup() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun appLaunchesSuccessfully() {
        // Arrange, Act...and Assert!
        // nothing to do all is in the setup!
    }

    @Test
    fun displaySnackOnEmptyQuery() {
        // Arrange, Act
        typeAndClick("")
            // Assert
            .checkIsDisplayed(homeSnackBarId)
    }

    @Test
    fun displayRightSnackTextOnBadCapitalTest(){
        // Arrange, Act
        typeAndClick("bad String")
            //Assert
            .checkViewContainsText(homeSnackBarId,getString(R.string.home_capital_snack_text_id))
    }

    @Test
    fun changeStepOnGoodCapitalLetterTest(){
        // Arrange, Act
        typeAndClick("Good String")
        //Assert
            .checkViewContainsText(homeTitleId,getString(R.string.home_rotation_title))
    }
}