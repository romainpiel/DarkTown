package com.romainpiel.darktown

import com.google.common.truth.Truth.assertThat
import com.romainpiel.darktown.markdown.MarkDownBrush
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner

@RunWith(RobolectricGradleTestRunner::class)
class SyntaxHighlighterTextWatcherTest {

    @Rule @JvmField
    var expectedException = ExpectedException.none()

    private var textWatcher: SyntaxHighlighterTextWatcher? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        textWatcher = SyntaxHighlighterTextWatcher(MarkDownBrush())
    }

    @Test
    fun findFirstCharacterOfLine_empty() {
        val position = textWatcher!!.findFirstCharacterOfLine("", 0)
        assertThat(position).isEqualTo(0)
    }

    @Test
    fun findFirstCharacterOfLine_outOfBound() {
        expectedException.expect(StringIndexOutOfBoundsException::class.java)
        textWatcher!!.findFirstCharacterOfLine("", 1)
    }

    @Test
    fun findFirstCharacterOfLine_withNewLineAtPosition() {
        val position = textWatcher!!.findFirstCharacterOfLine("a \n b", 2)
        assertThat(position).isEqualTo(0)
    }

    @Test
    fun findFirstCharacterOfLine_withNewLineBefore() {
        val position = textWatcher!!.findFirstCharacterOfLine("a \n b", 4)
        assertThat(position).isEqualTo(3)
    }

    @Test
    fun findFirstCharacterOfLine_noNewLineBefore() {
        val position = textWatcher!!.findFirstCharacterOfLine("a \n b", 1)
        assertThat(position).isEqualTo(0)
    }

    @Test
    fun findFirstNewLineCharAfter_empty() {
        val position = textWatcher!!.findFirstNewLineCharAfter("", 0)
        assertThat(position).isEqualTo(0)
    }

    @Test
    fun findFirstNewLineCharAfter_outOfBound() {
        expectedException.expect(StringIndexOutOfBoundsException::class.java)
        textWatcher!!.findFirstNewLineCharAfter("", 1)
    }

    @Test
    fun findFirstNewLineCharAfter_withNewLineAtPosition() {
        val position = textWatcher!!.findFirstNewLineCharAfter("a \n c", 2)
        assertThat(position).isEqualTo(2)
    }

    @Test
    fun findFirstNewLineCharAfter_withNewLineAfter() {
        val position = textWatcher!!.findFirstNewLineCharAfter("a \n c", 0)
        assertThat(position).isEqualTo(2)
    }

    @Test
    fun findFirstNewLineCharAfter_noNewLineAfter() {
        val position = textWatcher!!.findFirstNewLineCharAfter("a \n c", 3)
        assertThat(position).isEqualTo(5)
    }
}
