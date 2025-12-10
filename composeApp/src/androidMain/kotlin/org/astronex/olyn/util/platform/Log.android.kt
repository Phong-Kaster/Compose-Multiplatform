package org.astronex.olyn.util.platform

import android.util.Log

actual fun logcat(tag: String, message: String, enableHorizontalLine: Boolean) {

    if(enableHorizontalLine) {
        Log.d(tag, "==================================================")
    }

    Log.d(tag, message)
}