package eu.pbenayoun.capitalrotation.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import eu.pbenayoun.capitalrotation.R

enum class Step{
    CHECK_CAPITAL_LETTER,
    ROTATION_ANGLE
}

enum class CheckResult{
    OK,
    KO
}

data class TextsId(val title:Int, val editTextHint:Int, val buttonText: Int, val snackText: Int)



class HomeViewModel : ViewModel() {

    val CHECK_STEP_TEXTSID = TextsId(R.string.home_check_capital_title,R.string.home_check_capital_hint,R.string.home_button_check,R.string.home_capital_snack_text_id)
    val ROTATION_STEP_TEXTSID = TextsId(R.string.home_rotation_title,R.string.home_rotation_hint,R.string.home_button_rotate,R.string.home_rotation_snack_text_id)
    var currentStep = Step.CHECK_CAPITAL_LETTER
    var stepTextIds= CHECK_STEP_TEXTSID

    var currentQueryText  = ""

    fun setCurrentQuery(newQuery : String){
        currentQueryText = newQuery
    }

    fun checkQuery() : CheckResult{
        return checkCapitalQuery()
    }

    private fun checkCapitalQuery() : CheckResult{
        val checkResult = checkCapitalLetterPrefix(currentQueryText)
        currentQueryText=""
        if (checkResult){
            setStepRotation()
            return CheckResult.OK
        }
        else return CheckResult.KO
    }


    private fun checkCapitalLetterPrefix(string:String):Boolean{
        if (string.isNullOrEmpty()) return false
        return string[0].isLetter() && string[0].isUpperCase()
    }

    private fun setStepRotation(){
        currentQueryText=""
        currentStep=Step.ROTATION_ANGLE
        stepTextIds=ROTATION_STEP_TEXTSID
    }

}