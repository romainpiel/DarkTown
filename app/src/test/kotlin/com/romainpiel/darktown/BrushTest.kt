package com.romainpiel.darktown

import android.text.SpannableStringBuilder
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner

@RunWith(RobolectricGradleTestRunner::class)
class BrushTest {

    class EmptyBrush(override val symbolList: List<Symbol<*>> = emptyList()) : Brush

    @Test
    fun updateSpans_emptyBrush_emptyString() {
        val brush = EmptyBrush()
        val text = SpannableStringBuilder("")
        brush.updateSpans(text, 0, 0)
        assertThat(text.getSpans(0, text.length, Any::class.java)).isEmpty()
    }

    @Test
    fun updateSpans_emptyBrush_someString() {
        val brush = EmptyBrush()
        val text = SpannableStringBuilder("Logic will get you from A to B.\nImagination will take you everywhere.")
        brush.updateSpans(text, 0, 0)
        assertThat(text.getSpans(0, text.length, Any::class.java)).isEmpty()
    }
}