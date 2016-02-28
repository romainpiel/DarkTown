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

    override fun onTextChanged(text: CharSequence, start: Int, lengthBefore: Int, count: Int) {
        L.d("$text $start $lengthBefore $count")

        if (text !is Spannable) {
            return
        }

        val lineS = findFirstCharacterOfLine(text, start)
        val lineE = findFirstNewLineCharAfter(text, start + count)

        updateSpans(text, lineS, lineE)
    }

    override fun afterTextChanged(s: Editable) {
    }

    @VisibleForTesting
    internal fun updateSpans(text: Spannable, lineS: Int, lineE: Int) {
        if (lineS == lineE) {
            return
        }

        L.d("$lineS-$lineE")

        val subSequence = text.subSequence(lineS, lineE)

        L.d("${subSequence.toString()}")

        val matcher = pattern.matcher(subSequence)
        var foundSomething = false
        while (matcher.find() ) {
            foundSomething = true
            brush.symbolList.forEachIndexed { i, symbol ->
                val spans = text.getSpans(lineS, lineE, symbol.type)

                val groupId = i + 1 // matcher groups are 1-indexed
                if (matcher.group(groupId) != null) {
                    val matchS = matcher.start(groupId)
                    val matchE = matcher.end(groupId)
                    val span: HighlightedSpan
                    if (spans.size == 0) {
                        span = symbol.newSpan()
                    } else {
                        span = spans[0]
                    }
                    text.setSpan(span, matchS + lineS, matchE + lineS, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                } else {
                    for (span in spans) {
                        text.removeSpan(span)
                    }
                }
            }
        }

        if (!foundSomething) {
            val spans = text.getSpans(lineS, lineE, HighlightedSpan::class.java)
            for (span in spans) {
                text.removeSpan(span)
            }
        }
    }

    @VisibleForTesting
    internal fun findFirstCharacterOfLine(text: CharSequence, from: Int): Int {
        if (from < 0 || text.length < from) {
            throw StringIndexOutOfBoundsException()
        }

        var found = false
        var i = from
        while (!found && 0 <= i - 1) {
            if (text[i - 1] == '\n') {
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
