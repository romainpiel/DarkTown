package com.romainpiel.darktown

import java.util.regex.Pattern

interface Brush {
    val symbolList: List<Symbol<*>>

    fun toPattern(): Pattern {
        var strPattern = "^\\n?"
        symbolList.forEachIndexed { i, symbol ->
            if (i > 0) strPattern += "|"
            strPattern += "(${symbol.regexp})"
        }
        return Pattern.compile(strPattern)
    }
}