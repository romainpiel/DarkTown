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

    @Test
    fun italic_oneWord() {
        val text = SpannableStringBuilder("Come on, *you* are always right.")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, ItalicSpan::class.java)
        assertThat(spans).hasLength(1)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(9)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(14)
    }

    @Test
    fun italic_withSpace() {
        val text = SpannableStringBuilder("Come on, *you are* always right.")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, ItalicSpan::class.java)
        assertThat(spans).hasLength(1)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(9)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(18)
    }

    @Test
    fun italic_multiple() {
        val text = SpannableStringBuilder("Come on, *you* *are* always right.")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, ItalicSpan::class.java)
        assertThat(spans).hasLength(2)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(9)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(14)
        assertThat(text.getSpanStart(spans[1])).isEqualTo(15)
        assertThat(text.getSpanEnd(spans[1])).isEqualTo(20)
    }

    @Test
    fun italic_secondIncomplete() {
        val text = SpannableStringBuilder("Come on, *you* *are always right.")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, ItalicSpan::class.java)
        assertThat(spans).hasLength(1)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(9)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(14)
    }

    @Test
    fun italic_newLineInside() {
        val text = SpannableStringBuilder("Come on, *you\nare* always right.")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, ItalicSpan::class.java)
        assertThat(spans).isEmpty()
    }

    @Test
    fun italic_nothingInside() {
        val text = SpannableStringBuilder("Come on, ** you are always right.")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, ItalicSpan::class.java)
        assertThat(spans).isEmpty()
    }

    @Test
    fun italic_characterBefore() {
        val text = SpannableStringBuilder("Come on, you*are* always right.")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, ItalicSpan::class.java)
        assertThat(spans).isEmpty()
    }

    @Test
    fun italic_characterAfter() {
        val text = SpannableStringBuilder("Come on, *you*are always right.")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, ItalicSpan::class.java)
        assertThat(spans).isEmpty()
    }

    @Test
    fun italic_startLine() {
        val text = SpannableStringBuilder("*Come* on, you are always right.")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, ItalicSpan::class.java)
        assertThat(spans).hasLength(1)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(0)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(6)
    }

    @Test
    fun italic_commaAfter() {
        val text = SpannableStringBuilder("Come *on*, you are always right.")
        brush.updateSpans(text, 0, text.length)

        val spans = text.getSpans(0, text.length, ItalicSpan::class.java)
        assertThat(spans).hasLength(1)
        assertThat(text.getSpanStart(spans[0])).isEqualTo(5)
        assertThat(text.getSpanEnd(spans[0])).isEqualTo(9)
    }
}
