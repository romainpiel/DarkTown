package com.romainpiel.darktown.markdown

import android.text.TextPaint
import android.text.style.MetricAffectingSpan
import com.romainpiel.darktown.HighlightedSpan

class HeadingSpan : MetricAffectingSpan(), HighlightedSpan {

    override fun updateDrawState(tp: TextPaint) {
        tp.isFakeBoldText = true
        tp.textSize = tp.textSize * RELATIVE_SIZE
    }

    override fun updateMeasureState(tp: TextPaint) {
        tp.isFakeBoldText = true
        tp.textSize = tp.textSize * RELATIVE_SIZE
    }

    companion object {

        private val RELATIVE_SIZE = 1.2f
    }
}
