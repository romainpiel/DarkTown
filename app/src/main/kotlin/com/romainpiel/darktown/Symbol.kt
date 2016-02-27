package com.romainpiel.darktown

class Symbol<T: HighlightedSpan>(val regexp: String, val type: Class<T>) {
    companion object {
        inline operator fun <reified T : HighlightedSpan>invoke(regexp: String) = Symbol(regexp, T::class.java)
    }
}