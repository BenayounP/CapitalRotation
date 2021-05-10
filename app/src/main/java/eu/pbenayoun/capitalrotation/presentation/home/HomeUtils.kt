package eu.pbenayoun.capitalrotation.presentation.home

class HomeUtils() {
    fun checkCapitalLetterPrefix(string:String):Boolean{
        if (string.isNullOrEmpty()) return false
        return string[0].isLetter() && string[0].isUpperCase()
    }

    fun checkRotationAngle(string:String):Boolean{
        // check null or empty
        if (string.isNullOrEmpty()) return false
        // check string represents float
        var number=-1f
        try {
            number= string.toFloat()
        }
        catch (ex: NumberFormatException) {
            return false
        }
        // check angle
        val isCorrectAngle = number in 0f..360f
        return isCorrectAngle
    }

}