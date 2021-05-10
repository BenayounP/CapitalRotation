package eu.pbenayoun.capitalrotation

import eu.pbenayoun.capitalrotation.presentation.home.HomeViewModel
import org.junit.Test
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.Truth.assertWithMessage
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HomeUnitTest {

    // Subject under test
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel()
    }

    @Test
    fun testCheckCapitalLetter() {
        // Arrange
        // nothing to do

        // Act
        //nothing to do

        // Assert
        assertWithMessage("capital letter test: Empty").that(viewModel.CheckCapitalLetterPrefix("")).isFalse()
        assertWithMessage("capital letter test: Punctuation").that(viewModel.CheckCapitalLetterPrefix(".Liverpool")).isFalse()
        assertWithMessage("capital letter test: Lower case").that(viewModel.CheckCapitalLetterPrefix("liverpool")).isFalse()
        assertWithMessage("capital letter test: Number").that(viewModel.CheckCapitalLetterPrefix("6liverpool")).isFalse()
        assertWithMessage("capital letter test: Capital letter").that(viewModel.CheckCapitalLetterPrefix("Liverpool")).isTrue()
    }
}