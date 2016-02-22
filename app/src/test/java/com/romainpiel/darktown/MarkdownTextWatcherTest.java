package com.romainpiel.darktown;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;

import static com.google.common.truth.Truth.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
public class MarkdownTextWatcherTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private MarkdownTextWatcher textWatcher;

    @Before
    public void setUp() throws Exception {
        textWatcher = new MarkdownTextWatcher();
    }

    @Test
    public void findFirstNewLineCharBefore_empty() {
        int position = textWatcher.findFirstNewLineCharBefore("", 0);
        assertThat(position).isEqualTo(0);
    }

    @Test
    public void findFirstNewLineCharBefore_outOfBound() {
        expectedException.expect(StringIndexOutOfBoundsException.class);
        textWatcher.findFirstNewLineCharBefore("", 1);
    }

    @Test
    public void findFirstNewLineCharBefore_withNewLineAtPosition() {
        int position = textWatcher.findFirstNewLineCharBefore("a \n b", 2);
        assertThat(position).isEqualTo(2);
    }

    @Test
    public void findFirstNewLineCharBefore_withNewLineBefore() {
        int position = textWatcher.findFirstNewLineCharBefore("a \n b", 4);
        assertThat(position).isEqualTo(2);
    }

    @Test
    public void findFirstNewLineCharBefore_noNewLineBefore() {
        int position = textWatcher.findFirstNewLineCharBefore("a \n b", 1);
        assertThat(position).isEqualTo(0);
    }

    @Test
    public void findFirstNewLineCharAfter_empty() {
        int position = textWatcher.findFirstNewLineCharAfter("", 0);
        assertThat(position).isEqualTo(0);
    }

    @Test
    public void findFirstNewLineCharAfter_outOfBound() {
        expectedException.expect(StringIndexOutOfBoundsException.class);
        textWatcher.findFirstNewLineCharAfter("", 1);
    }

    @Test
    public void findFirstNewLineCharAfter_withNewLineAtPosition() {
        int position = textWatcher.findFirstNewLineCharAfter("a \n c", 2);
        assertThat(position).isEqualTo(2);
    }

    @Test
    public void findFirstNewLineCharAfter_withNewLineAfter() {
        int position = textWatcher.findFirstNewLineCharAfter("a \n c", 0);
        assertThat(position).isEqualTo(2);
    }

    @Test
    public void findFirstNewLineCharAfter_noNewLineAfter() {
        int position = textWatcher.findFirstNewLineCharAfter("a \n c", 3);
        assertThat(position).isEqualTo(5);
    }
}
