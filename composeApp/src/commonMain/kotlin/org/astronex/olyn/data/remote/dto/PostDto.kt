package org.astronex.olyn.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class PostDto(
    val id: Int? = null,
    val userId: Int,
    val title: String,
    val body: String,
)

@Serializable
data class PostCreateDto(
    val title: String,
    val body: String,
    val userId: Int,
)
