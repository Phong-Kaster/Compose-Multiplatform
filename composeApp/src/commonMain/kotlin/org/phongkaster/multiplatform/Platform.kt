package org.phongkaster.multiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform