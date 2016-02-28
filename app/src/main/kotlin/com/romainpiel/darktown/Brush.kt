package com.romainpiel.darktown

import android.support.annotation.VisibleForTesting
import android.text.Spannable
import android.text.Spanned

interface Brush {
    val symbolList: List<Symbol<*>>

    @VisibleForTesting
    fun updateSpans(text: Spannable, start: Int, end: Int) {
        if (start == end) {
            return
        }

        L.d("$start-$end")

        val subSequence = text.subSequence(start, end)

        L.d("${subSequence.toString()}")

        symbolList.forEach { symbol ->
            val matcher = symbol.pattern.matcher(subSequence)
            val spans = text.getSpans(start, end, symbol.type)

            var foundSomething = false
            while(matcher.find()) {
                foundSomething = true

                val matchS = matcher.start()
                val matchE = matcher.end()
                val span: HighlightedSpan
                if (spans.size == 0) {
                    span = symbol.newSpan()
                } else {
                    span = spans[0]
                }
                text.setSpan(span, matchS + start, matchE + start, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            if (!foundSomething) {
                for (span in spans) {
                    text.removeSpan(span)
                }
            }
        }
    }
}