package org.astronex.olyn.util.platform

actual fun logcat(tag: String, message: String, enableHorizontalLine: Boolean) {
    if (enableHorizontalLine) {
        println("$tag - ==================================================")
    }
    println("$tag - $message")
}