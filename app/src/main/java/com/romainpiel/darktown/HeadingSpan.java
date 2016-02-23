package com.romainpiel.darktown;

import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class HeadingSpan extends MetricAffectingSpan implements MarkDownSpan {

    private static final float RELATIVE_SIZE = 1.2f;

    @Override
    public void updateDrawState(TextPaint tp) {
        tp.setFakeBoldText(true);
        tp.setTextSize(tp.getTextSize() * RELATIVE_SIZE);
    }

    @Override
    public void updateMeasureState(TextPaint tp) {
        tp.setFakeBoldText(true);
        tp.setTextSize(tp.getTextSize() * RELATIVE_SIZE);
    }
}
