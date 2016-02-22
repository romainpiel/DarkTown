package com.romainpiel.darktown;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.EditText;

public class MarkdownEditText extends EditText {

    private final MarkdownTextWatcher textWatcher;

    public MarkdownEditText(Context context) {
        super(context);
        textWatcher = new MarkdownTextWatcher();
        initWatcher();
    }

    public MarkdownEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        textWatcher = new MarkdownTextWatcher();
        initWatcher();
    }

    public MarkdownEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        textWatcher = new MarkdownTextWatcher();
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
