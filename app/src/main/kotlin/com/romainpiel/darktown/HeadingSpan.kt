package com.romainpiel.darktown

import android.text.TextPaint
import android.text.style.MetricAffectingSpan

class HeadingSpan : MetricAffectingSpan(), MarkDownSpan {

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
