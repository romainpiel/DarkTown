package com.romainpiel.darktown

import android.support.annotation.VisibleForTesting
import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher

class SyntaxHighlighterTextWatcher(private val brush: Brush) : TextWatcher {

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(text: CharSequence, start: Int, lengthBefore: Int, count: Int) {
        L.d("$text $start $lengthBefore $count")

        if (text !is Spannable) {
            return
        }

        val lineS = findFirstCharacterOfLine(text, start)
        val lineE = findFirstNewLineCharAfter(text, start + count)

        brush.updateSpans(text, lineS, lineE)
    }

    override fun afterTextChanged(s: Editable) {
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
