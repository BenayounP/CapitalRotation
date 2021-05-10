package eu.pbenayoun.capitalrotation.presentation.home


/** Utility class to check if :
 *  - string begin with a capital
 *  - string represents a angle between 0 and 360
 */
class HomeUtils() {
    fun checkCapitalLetterPrefix(string:String):Boolean{
        if (string.isNullOrEmpty()) return false
        return string[0].isLetter() && string[0].isUpperCase()
    }

    fun checkRotationAngle(string:String):CheckResponse{
        // check null or empty
        if (string.isNullOrEmpty()) return CheckResponse(CheckResult.KO)
        // check string represents float
        var number=-1f
        try {
            number= string.toFloat()
        }
        catch (ex: NumberFormatException) {
            return CheckResponse(CheckResult.KO)
        }
        // check angle
        val isCorrectAngle = number in 0f..360f
        if (isCorrectAngle) return CheckResponse(CheckResult.OK,number)
        return CheckResponse(CheckResult.KO)
    }
}