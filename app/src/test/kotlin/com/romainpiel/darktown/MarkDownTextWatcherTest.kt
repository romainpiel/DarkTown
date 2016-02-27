package com.romainpiel.darktown

import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import org.robolectric.RobolectricGradleTestRunner

@RunWith(RobolectricGradleTestRunner::class)
class MarkDownTextWatcherTest {

    @Rule @JvmField
    var expectedException = ExpectedException.none()

    private var textWatcher: MarkDownTextWatcher? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        textWatcher = MarkDownTextWatcher()
    }

    @Test
    fun findFirstNewLineCharBefore_empty() {
        val position = textWatcher!!.findFirstNewLineCharBefore("", 0)
        assertThat(position).isEqualTo(0)
    }

    @Test
    fun findFirstNewLineCharBefore_outOfBound() {
        expectedException.expect(StringIndexOutOfBoundsException::class.java)
        textWatcher!!.findFirstNewLineCharBefore("", 1)
    }

    @Test
    fun findFirstNewLineCharBefore_withNewLineAtPosition() {
        val position = textWatcher!!.findFirstNewLineCharBefore("a \n b", 2)
        assertThat(position).isEqualTo(2)
    }

    @Test
    fun findFirstNewLineCharBefore_withNewLineBefore() {
        val position = textWatcher!!.findFirstNewLineCharBefore("a \n b", 4)
        assertThat(position).isEqualTo(2)
    }

    @Test
    fun findFirstNewLineCharBefore_noNewLineBefore() {
        val position = textWatcher!!.findFirstNewLineCharBefore("a \n b", 1)
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
