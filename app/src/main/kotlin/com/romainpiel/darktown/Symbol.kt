package com.romainpiel.darktown

import android.content.res.Resources

class Symbol<T: HighlightedSpan>(
        val resources: Resources,
        val regexp: String,
        val type: Class<T>,
        val newSpan: () -> T) {
    companion object {
        inline operator fun <reified T : HighlightedSpan>invoke(resources: Resources, regexp: String, noinline newSpan: () -> T)
                = Symbol(resources, regexp, T::class.java, newSpan)
    }
}