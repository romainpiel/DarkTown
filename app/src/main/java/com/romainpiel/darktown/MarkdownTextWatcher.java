package com.romainpiel.darktown;

import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextWatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class MarkdownTextWatcher implements TextWatcher {

    private final Pattern pattern;

    MarkdownTextWatcher() {
        pattern = Pattern.compile("^\\n?#+\\s+.*");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {

        if (!(text instanceof Spannable)) {
            return;
        }

        Spannable spannableText = (Spannable) text;

        int s = ensureRange(start - lengthBefore, 0, spannableText.length());
        int e = ensureRange(start - lengthBefore + lengthAfter + 1, 0, spannableText.length());

        s = findFirstNewLineCharBefore(spannableText, s);
        e = findFirstNewLineCharAfter(text, e);

        if (s == e) {
            return;
        }

        HeadingSpan[] spans = spannableText.getSpans(s, e, HeadingSpan.class);

        CharSequence subSequence = spannableText.subSequence(s, e);

        Matcher matcher = pattern.matcher(subSequence);
        int count = 0;
        while (matcher.find()) {
            int matchS = matcher.start();
            int matchE = matcher.end();
            HeadingSpan span;
            if (spans.length == 0) {
                span = new HeadingSpan();
            } else {
                span = spans[0];
            }
            spannableText.setSpan(span, matchS + s, matchE + s, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            count++;
        }

        if (count == 0) {
            for (HeadingSpan span : spans) {
                spannableText.removeSpan(span);
            }
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    private int ensureRange(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    private int findFirstNewLineCharBefore(CharSequence text, int from) {
        boolean found = false;
        int i = from;
        while (!found && i > 0) {
            if (text.charAt(i) == '\n') {
                found = true;
            } else {
                i--;
            }
        }
        return i;
    }

    private int findFirstNewLineCharAfter(CharSequence text, int from) {
        boolean found = false;
        int i = from;
        while (!found && i < text.length()) {
            if (text.charAt(i) == '\n') {
                found = true;
            } else {
                i++;
            }
        }
        return i;
    }
}
