package com.romainpiel.darktown

import android.support.annotation.VisibleForTesting
import android.text.Editable
import android.text.Spannable
import android.text.Spanned
import android.text.TextWatcher
import java.util.regex.Pattern

class SyntaxHighlighterTextWatcher : TextWatcher {

    private val pattern: Pattern
    private val brush: Brush

    constructor(brush: Brush) {
        this.brush = brush
        pattern = brush.toPattern()
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(text: CharSequence, start: Int, lengthBefore: Int, lengthAfter: Int) {

        if (text !is Spannable) {
            return
        }

        var s = ensureRange(start - lengthBefore, 0, text.length)
        var e = ensureRange(start - lengthBefore + lengthAfter + 1, 0, text.length)

        s = findFirstNewLineCharBefore(text, s)
        e = findFirstNewLineCharAfter(text, e)

        if (s == e) {
            return
        }

        val subSequence = text.subSequence(s, e)

        val matcher = pattern.matcher(subSequence)
        var foundSomething = false
        while (matcher.find() ) {
            foundSomething = true
            brush.symbolList.forEachIndexed { i, symbol ->
                val spans = text.getSpans(s, e, symbol.type)

                val groupId = i + 1 // matcher groups are 1-indexed
                if (matcher.group(groupId) != null) {
                    val matchS = matcher.start(groupId)
                    val matchE = matcher.end(groupId)
                    val span: HighlightedSpan
                    if (spans.size == 0) {
                        span = symbol.type.newInstance()
                    } else {
                        span = spans[0]
                    }
                    text.setSpan(span, matchS + s, matchE + s, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                } else {
                    for (span in spans) {
                        text.removeSpan(span)
                    }
                }
            }
        }

        if (!foundSomething) {
            val spans = text.getSpans(s, e, HighlightedSpan::class.java)
            for (span in spans) {
                text.removeSpan(span)
            }
        }
    }

    override fun afterTextChanged(s: Editable) {
    }

    private fun ensureRange(value: Int, min: Int, max: Int): Int {
        return Math.min(Math.max(value, min), max)
    }

    @VisibleForTesting
    internal fun findFirstNewLineCharBefore(text: CharSequence, from: Int): Int {
        if (from < 0 || text.length < from) {
            throw StringIndexOutOfBoundsException()
        }

        var found = false
        var i = from
        while (!found && i > 0) {
            if (text[i] == '\n') {
                found = true
            } else {
                i--
            }
        }
        return i
    }

    @VisibleForTesting
    internal fun findFirstNewLineCharAfter(text: CharSequence, from: Int): Int {
        if (from < 0 || text.length < from) {
            throw StringIndexOutOfBoundsException()
        }

        var found = false
        var i = from
        while (!found && i < text.length) {
            if (text[i] == '\n') {
                found = true
            } else {
                i++
            }
        }
        return i
    }
}
