package com.romainpiel.darktown

class Symbol<T: HighlightedSpan>(
        val regexp: String,
        val type: Class<T>,
        val newSpan: () -> T) {
    companion object {
        inline operator fun <reified T : HighlightedSpan>invoke(regexp: String, noinline newSpan: () -> T)
                = Symbol(regexp, T::class.java, newSpan)
    }
}