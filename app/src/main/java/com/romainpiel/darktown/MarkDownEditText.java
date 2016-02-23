package com.romainpiel.darktown;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.EditText;

public class MarkDownEditText extends EditText {

    private final MarkDownTextWatcher textWatcher;

    public MarkDownEditText(Context context) {
        super(context);
        textWatcher = new MarkDownTextWatcher();
        initWatcher();
    }

    public MarkDownEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        textWatcher = new MarkDownTextWatcher();
        initWatcher();
    }

    public MarkDownEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textWatcher = new MarkDownTextWatcher();
        initWatcher();
    }

    private void initWatcher() {
        removeTextChangedListener(textWatcher);
        addTextChangedListener(textWatcher);
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        initWatcher();
    }
}
