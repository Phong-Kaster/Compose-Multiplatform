package org.astronex.olyn.util.platform

expect fun logcat(
    tag: String = "Compose Mutiplatform",
    message: String = "",
    enableHorizontalLine: Boolean = false
)