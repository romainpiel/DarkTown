package com.romainpiel.darktown

import java.util.regex.Pattern

class Symbol<T: HighlightedSpan>(
        val pattern: Pattern,
        val type: Class<T>,
        val newSpan: () -> T) {
    companion object {
        inline operator fun <reified T : HighlightedSpan>invoke(regexp: String, noinline newSpan: () -> T)
                = Symbol(Pattern.compile(regexp), T::class.java, newSpan)
    }
}