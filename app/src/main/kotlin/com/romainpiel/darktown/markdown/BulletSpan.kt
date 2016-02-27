package com.romainpiel.darktown.markdown

import android.content.res.Resources
import android.text.style.LeadingMarginSpan
import com.romainpiel.darktown.HighlightedSpan
import com.romainpiel.darktown.R

class BulletSpan(val resources: Resources) :
        LeadingMarginSpan.Standard(-resources.getDimensionPixelOffset(R.dimen.spacing_double), 0),
        HighlightedSpan