package com.romainpiel.darktown

import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.EditText

class SyntaxHighlighterEditText : EditText {

    private var textWatcher: SyntaxHighlighterTextWatcher? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    fun setBrush(brush : Brush) {
        textWatcher = SyntaxHighlighterTextWatcher(brush)
        initWatcher()
    }

    private fun initWatcher() {
        if (textWatcher != null) {
            removeTextChangedListener(textWatcher)
            addTextChangedListener(textWatcher)
        }
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        super.onRestoreInstanceState(state)
        initWatcher()
    }
}
