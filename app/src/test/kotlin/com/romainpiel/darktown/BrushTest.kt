package com.romainpiel.darktown

import android.text.SpannableStringBuilder
import android.text.Spanned
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner

@RunWith(RobolectricGradleTestRunner::class)
class BrushTest {

    class YoloSpan() : HighlightedSpan

    class EmptyBrush(override val symbolList: List<Symbol<*>> = emptyList()) : Brush
    class YoloBrush() : Brush {
        override val symbolList: List<Symbol<*>> = listOf(
                Symbol<YoloSpan>("yolo", { YoloSpan() })
        )
    }

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

    @Test
    fun updateSpans_singleSymbolBrush_oneMatch() {
        val brush = YoloBrush()
        val text = SpannableStringBuilder("hey mate yolo hashtag it")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, YoloSpan::class.java)
        assertThat(spans).hasLength(1)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(9)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(13)
    }

    @Test
    fun updateSpans_singleSymbolBrush_twoMatches() {
        val brush = YoloBrush()
        val text = SpannableStringBuilder("hey mate yolo hashtag it yolo!")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, YoloSpan::class.java)
        assertThat(spans).hasLength(2)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(9)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(13)
        assertThat(text.getSpanStart(spans[1])).isEqualTo(25)
        assertThat(text.getSpanEnd(spans[1])).isEqualTo(29)
    }

    @Test
    fun updateSpans_singleSymbolBrush_oneMatch_alreadyOneMatch() {
        val brush = YoloBrush()
        val text = SpannableStringBuilder("hey mate yolo hashtag it")
        text.setSpan(YoloSpan(), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, YoloSpan::class.java)
        assertThat(spans).hasLength(1)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(9)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(13)
    }

    @Test
    fun updateSpans_singleSymbolBrush_oneMatch_alreadyTwoMatches() {
        val brush = YoloBrush()
        val text = SpannableStringBuilder("hey mate yolo hashtag it")
        text.setSpan(YoloSpan(), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        text.setSpan(YoloSpan(), 4, 5, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, YoloSpan::class.java)
        assertThat(spans).hasLength(1)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(9)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(13)
    }
}