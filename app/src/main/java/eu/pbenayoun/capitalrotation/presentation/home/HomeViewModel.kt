package eu.pbenayoun.capitalrotation.presentation.home

import androidx.lifecycle.ViewModel
import eu.pbenayoun.capitalrotation.R


/** Main class of the app. The business is here!
 *  - Two steps/states for managing the form-field for checking string beginning with capital letter Or rotation angle
 *  - manage texts models for title, model-form hints and error snackBar
 *  - manage transition between setsp/state and with it's fragment and RotationFragment
 */



enum class Step{
    CAPITAL_LETTER_CHECK,
    ROTATION_ANGLE_CHECK,
}

// basic result for a check of capital or angle
enum class CheckResult{
    OK,
    KO
}

// represents a response to a check (capital or angle between 0 and 360)
data class CheckResponse(val result: CheckResult, val rotationAngle:Float=-1f)

// used for texts models for title, model-form hints and error snackBar
data class TextsId(val title:Int, val editTextHint:Int, val buttonText: Int, val snackText: Int)


class HomeViewModel : ViewModel() {

    val CHECK_STEP_TEXTSID = TextsId(R.string.home_check_capital_title,R.string.home_check_capital_hint,R.string.home_button_check,R.string.home_capital_snack_text_id)
    val ROTATION_STEP_TEXTSID = TextsId(R.string.home_rotation_title,R.string.home_rotation_hint,R.string.home_button_rotate,R.string.home_rotation_snack_text_id)
    var currentStep = Step.CAPITAL_LETTER_CHECK
    var stepTextIds= CHECK_STEP_TEXTSID

    var currentQueryText  = ""

    private val homeUtils=HomeUtils()

    fun setCurrentQuery(newQuery : String){
        currentQueryText = newQuery
    }

    fun checkQuery() : CheckResponse{
        return when(currentStep){
            Step.CAPITAL_LETTER_CHECK ->  checkCapitalQuery()
            Step.ROTATION_ANGLE_CHECK-> checkRotationAngle()
        }
    }

    private fun checkCapitalQuery() : CheckResponse{
        val checkResult = homeUtils.checkCapitalLetterPrefix(currentQueryText)
        if (checkResult){
            currentQueryText=""
            return CheckResponse(CheckResult.OK)
        }
        else return CheckResponse(CheckResult.KO)
    }


    fun setStepRotation(){
        currentQueryText=""
        currentStep=Step.ROTATION_ANGLE_CHECK
        stepTextIds=ROTATION_STEP_TEXTSID
    }

    private fun checkRotationAngle() : CheckResponse{
        return  homeUtils.checkRotationAngle(currentQueryText)
    }
}