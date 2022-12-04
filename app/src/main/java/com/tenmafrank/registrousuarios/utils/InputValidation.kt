package com.tenmafrank.registrousuarios.utils

import android.util.Log

class InputValidation {
    fun validate(input: String): Int{

        var haveEmoji = false

        for (char in input){
            var type = Character.getType(char)
            if (type == Character.SURROGATE.toInt() || type == Character.OTHER_SYMBOL.toInt() ){
                Log.i("char", " typo$type")
                haveEmoji = true
            }
        }

        var level = 0
        if(input.isNullOrEmpty()){
            level = 1
        }
        else if (haveEmoji){
            level = 2
        }
        return level
    }
}