package com.romainpiel.darktown

import android.app.Application
import android.support.v7.app.AppCompatDelegate

class DarkTownApplication: Application() {
    companion object {
        init {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO)
        }
    }
}
