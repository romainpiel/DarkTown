package com.romainpiel.darktown.markdown

import android.text.SpannableStringBuilder
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricGradleTestRunner::class)
class MarkDownBrushTest {

    lateinit var brush: MarkDownBrush

    @Before
    fun setUp() {
        brush = MarkDownBrush(RuntimeEnvironment.application.resources)
    }

    @Test
    fun heading_h1() {
        val text = SpannableStringBuilder("# a title")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, HeadingSpan::class.java)
        assertThat(spans).hasLength(1)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(0)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(text.length)
    }

    @Test
    fun heading_h2() {
        val text = SpannableStringBuilder("## a subtitle")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, HeadingSpan::class.java)
        assertThat(spans).hasLength(1)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(0)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(text.length)
    }

    @Test
    fun heading_noSpace() {
        val text = SpannableStringBuilder("#not a title")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, HeadingSpan::class.java)
        assertThat(spans).isEmpty()
    }

    @Test
    fun heading_textBefore() {
        val text = SpannableStringBuilder("blah # not a title")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, HeadingSpan::class.java)
        assertThat(spans).isEmpty()
    }

    @Test
    fun bullet_correctFormatting() {
        val text = SpannableStringBuilder("- list item")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, BulletSpan::class.java)
        assertThat(spans).hasLength(1)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(0)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(text.length)
    }

    @Test
    fun bullet_twoDash() {
        val text = SpannableStringBuilder("-- not a list item")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, BulletSpan::class.java)
        assertThat(spans).isEmpty()
    }

    @Test
    fun bullet_noSpace() {
        val text = SpannableStringBuilder("-not a list item")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, BulletSpan::class.java)
        assertThat(spans).isEmpty()
    }

    @Test
    fun bullet_textBefore() {
        val text = SpannableStringBuilder("blah - not a list item")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, BulletSpan::class.java)
        assertThat(spans).isEmpty()
    }
}
