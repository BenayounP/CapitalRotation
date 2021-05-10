package eu.pbenayoun.capitalrotation.util

import android.app.Activity
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.CoreMatchers

/**
 * Inspired by :
 * https://www.raywenderlich.com/949489-espresso-testing-and-screen-robots-getting-started
 */

class ScreenRobot {

    private var activityContext: Activity? = null // Only required for some calls

    fun checkIsDisplayed(@IdRes vararg viewIds: Int): ScreenRobot {
        for (viewId in viewIds) {
            Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
        return this
    }

    fun checkIsHidden(@IdRes vararg viewIds: Int): ScreenRobot {
        for (viewId in viewIds) {
            Espresso.onView(ViewMatchers.withId(viewId))
                .check(ViewAssertions.matches(CoreMatchers.not(ViewMatchers.isDisplayed())))
        }
        return this
    }

    fun checkViewContainsText(@IdRes viewId: Int, expected: String): ScreenRobot {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.withText(CoreMatchers.containsString(expected))))
        return this
    }

    fun checkViewMatchText(@IdRes viewId: Int, @StringRes messageResId: Int) : ScreenRobot {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.withText(messageResId)))
        return this
    }


    fun checkViewHasHint(@IdRes viewId: Int, @StringRes messageResId: Int) : ScreenRobot {
        Espresso.onView(ViewMatchers.withId(viewId))
            .check(ViewAssertions.matches(ViewMatchers.withHint(messageResId)))
        return this
    }

    fun clickOkOnView(@IdRes viewId: Int) : ScreenRobot {
        Espresso.onView(ViewMatchers.withId(viewId))
            .perform(ViewActions.click())
        return this
    }

    fun enterTextIntoView(@IdRes viewId: Int, text: String): ScreenRobot {
        Espresso.onView(ViewMatchers.withId(viewId))
            .perform(ViewActions.replaceText(text))
        return this
    }

    fun provideActivityContext(activityContext: Activity): ScreenRobot {
        this.activityContext = activityContext
        return this
    }


    companion object {

        private var instance: ScreenRobot? = null

        fun getInstance() : ScreenRobot {
            if (instance ==null)
                instance =
                    ScreenRobot()
            return instance!!
        }

    }
}