package eu.pbenayoun.capitalrotation.presentation.home

import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {


    fun CheckCapitalLetterPrefix(string:String):Boolean{
        if (string.isNullOrEmpty()) return false
        return string[0].isLetter() && string[0].isUpperCase()
    }

}