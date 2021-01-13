package com.abkhrr.movieeplot.utils.android.log

import android.util.Log

internal object MyLog {

    private const val LOGGING = true

    fun d(tag: String, message: String) {
        if (LOGGING) {
            Log.d(tag, message)
        }
    }

    fun e(tag: String, message: String) {
        if (LOGGING) {
            Log.d(tag, message)
        }
    }

    fun v(tag: String, message: String) {
        if (LOGGING) {
            Log.d(tag, message)
        }
    }

}