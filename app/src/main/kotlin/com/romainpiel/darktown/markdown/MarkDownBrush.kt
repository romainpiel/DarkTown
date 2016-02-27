package com.romainpiel.darktown.markdown

import com.romainpiel.darktown.Brush
import com.romainpiel.darktown.Symbol

class MarkDownBrush() : Brush {
    override val symbolList: List<Symbol> = listOf(
            Symbol("#+\\s+.*", { HeadingSpan() })
    )
}