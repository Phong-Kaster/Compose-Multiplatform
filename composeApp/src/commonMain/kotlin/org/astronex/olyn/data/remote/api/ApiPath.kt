package org.astronex.olyn.data.remote.api

/**
 * Relative paths for Ktor requests (resolved against [org.astronex.olyn.core.config.AppConfig.apiBaseUrl]).
 * Add one section per backend resource (users, items, …) so routes stay searchable and reusable.
 */
object ApiPath {
    const val POSTS: String = "posts"

    /** Single post by id, e.g. `posts/1`. */
    fun post(id: Int): String = "$POSTS/$id"
}
