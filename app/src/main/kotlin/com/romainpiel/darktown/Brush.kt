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

        val subSequence = text.subSequence(start, end)
        symbolList.forEach { symbol ->
            val matcher = symbol.pattern.matcher(subSequence)
            val groupCount = matcher.groupCount()
            val spans = text.getSpans(start, end, symbol.type)
            var nextRecycledSpan = 0

            while (matcher.find()) {
                val matchS = matcher.start(groupCount)
                val matchE = matcher.end(groupCount)
                val span: HighlightedSpan
                if (nextRecycledSpan < spans.size) {
                    span = spans[nextRecycledSpan]
                    nextRecycledSpan++
                } else {
                    span = symbol.newSpan()
                }
                text.setSpan(span, matchS + start, matchE + start, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            for (i in nextRecycledSpan..spans.size - 1) {
                text.removeSpan(spans[i])
            }
        }
    }
}