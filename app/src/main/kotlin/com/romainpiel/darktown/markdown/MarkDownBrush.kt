package com.romainpiel.darktown.markdown

import android.content.res.Resources
import com.romainpiel.darktown.Brush
import com.romainpiel.darktown.Symbol

class MarkDownBrush(val resources: Resources) : Brush {
    override val symbolList: List<Symbol<*>> = listOf(
            Symbol<HeadingSpan>("^#+\\s+.*$") { HeadingSpan() },
            Symbol<BulletSpan>("^-\\s+.*$") { BulletSpan(resources) }
    )
}