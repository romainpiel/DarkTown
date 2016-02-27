package com.romainpiel.darktown

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.bindView
import com.romainpiel.darktown.markdown.MarkDownBrush


class EditorActivity : AppCompatActivity() {

    private val toolbar: Toolbar by bindView(R.id.toolbar)
    private val input: SyntaxHighlighterEditText by bindView(R.id.input)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)
        setSupportActionBar(toolbar)
        input.setBrush(MarkDownBrush(resources))
    }
}
