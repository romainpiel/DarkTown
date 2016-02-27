package com.romainpiel.darktown

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.EditText

class MarkDownEditText : EditText {

    private val textWatcher: MarkDownTextWatcher

    constructor(context: Context) : super(context) {
        textWatcher = MarkDownTextWatcher()
        initWatcher()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        textWatcher = MarkDownTextWatcher()
        initWatcher()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        textWatcher = MarkDownTextWatcher()
        initWatcher()
    }

    private fun initWatcher() {
        removeTextChangedListener(textWatcher)
        addTextChangedListener(textWatcher)
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        super.onRestoreInstanceState(state)
        initWatcher()
    }
}
