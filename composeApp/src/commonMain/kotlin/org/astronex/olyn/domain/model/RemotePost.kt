package org.astronex.olyn.domain.model

/** Domain shape for a blog-style post returned by the sample remote API (JSONPlaceholder). */
data class RemotePost(
    val id: Int?,
    val userId: Int,
    val title: String,
    val body: String,
)
