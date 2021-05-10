package eu.pbenayoun.capitalrotation.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import eu.pbenayoun.capitalrotation.R

enum class Step{
    CAPITAL_LETTER_CHECK,
    ROTATION_ANGLE_CHECK,
}

enum class CheckResult{
    OK,
    KO
}

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

    fun checkQuery() : CheckResult{
        Log.d("TMP_DEBUG", "check query: ${currentStep.name}")
        return when(currentStep){
            Step.CAPITAL_LETTER_CHECK ->  checkCapitalQuery()
            Step.ROTATION_ANGLE_CHECK-> checkRotationAngle()
            else -> CheckResult.KO
        }
    }

    private fun checkCapitalQuery() : CheckResult{
        val checkResult = homeUtils.checkCapitalLetterPrefix(currentQueryText)
        if (checkResult){
            currentQueryText=""
            return CheckResult.OK
        }
        else return CheckResult.KO
    }


    fun setStepRotation(){
        currentQueryText=""
        currentStep=Step.ROTATION_ANGLE_CHECK
        stepTextIds=ROTATION_STEP_TEXTSID
    }

    private fun checkRotationAngle() : CheckResult{
        val test =  homeUtils.checkRotationAngle(currentQueryText)
        return when (test){
            true -> CheckResult.OK
            false -> CheckResult.KO
        }
    }
}