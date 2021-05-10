package eu.pbenayoun.capitalrotation

import org.junit.Test
import com.google.common.truth.Truth.assertWithMessage
import eu.pbenayoun.capitalrotation.presentation.home.CheckResult
import eu.pbenayoun.capitalrotation.presentation.home.CheckResponse
import eu.pbenayoun.capitalrotation.presentation.home.HomeUtils
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class HomeUtilsTest {

    // Subject under test
    private lateinit var homeUtils: HomeUtils

    @Before
    fun setup() {
        homeUtils = HomeUtils()
    }

    @Test
    fun testCheckCapitalLetterInput() {
        // Arrange
        // nothing to do

        // Act
        //nothing to do

        // Assert
        assertWithMessage("capital letter test: Empty").that(homeUtils.checkCapitalLetterPrefix("")).isFalse()
        assertWithMessage("capital letter test: Punctuation").that(homeUtils.checkCapitalLetterPrefix(".Liverpool")).isFalse()
        assertWithMessage("capital letter test: Lower case").that(homeUtils.checkCapitalLetterPrefix("liverpool")).isFalse()
        assertWithMessage("capital letter test: Number").that(homeUtils.checkCapitalLetterPrefix("6liverpool")).isFalse()
        assertWithMessage("capital letter test: Capital letter").that(homeUtils.checkCapitalLetterPrefix("Liverpool")).isTrue()
    }

    @Test
    fun testCheckAngleInput() {
        // Arrange
        // nothing to do

        // Act
        //nothing to do

        // Assert
        assertWithMessage("check angle input: Empty").that(homeUtils.checkRotationAngle("")).isEqualTo(CheckResponse(CheckResult.KO))
        assertWithMessage("check angle input: Punctuation").that(homeUtils.checkRotationAngle(".")).isEqualTo(CheckResponse(CheckResult.KO))
        assertWithMessage("check angle input: text").that(homeUtils.checkRotationAngle("liverpool")).isEqualTo(CheckResponse(CheckResult.KO))
        assertWithMessage("check angle input: bad number").that(homeUtils.checkRotationAngle("45,7")).isEqualTo(CheckResponse(CheckResult.KO))
        assertWithMessage("check angle input: Number negative").that(homeUtils.checkRotationAngle("-1")).isEqualTo(CheckResponse(CheckResult.KO))
        assertWithMessage("check angle input: Number above 360").that(homeUtils.checkRotationAngle("360.1")).isEqualTo(CheckResponse(CheckResult.KO))
        assertWithMessage("check angle input: Number 0").that(homeUtils.checkRotationAngle("0")).isEqualTo(CheckResponse(CheckResult.OK,0f))
        assertWithMessage("check angle input: Number int in range").that(homeUtils.checkRotationAngle("45")).isEqualTo(CheckResponse(CheckResult.OK,45f))
        assertWithMessage("check angle input: Number float in range").that(homeUtils.checkRotationAngle("45.6")).isEqualTo(CheckResponse(CheckResult.OK,45.6f))
        assertWithMessage("check angle input: Number 360").that(homeUtils.checkRotationAngle("360")).isEqualTo(CheckResponse(CheckResult.OK,360f))
    }
}