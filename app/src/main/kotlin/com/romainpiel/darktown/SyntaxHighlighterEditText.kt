package com.romainpiel.darktown

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.EditText

class SyntaxHighlighterEditText : EditText {

    private val textWatcher: SyntaxHighlighterTextWatcher

    constructor(context: Context) : super(context) {
        textWatcher = SyntaxHighlighterTextWatcher()
        initWatcher()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        textWatcher = SyntaxHighlighterTextWatcher()
        initWatcher()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        textWatcher = SyntaxHighlighterTextWatcher()
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
