package org.astronex.olyn.domain.repository

import org.astronex.olyn.common.Resource
import org.astronex.olyn.domain.model.RemotePost

/**
 * Contract for remote post CRUD. Domain layer depends on this interface, not on Ktor or DTOs.
 */
interface PostRepository {
    suspend fun list(): Resource<List<RemotePost>>
    suspend fun getById(id: Int): Resource<RemotePost>
    suspend fun create(title: String, body: String, userId: Int): Resource<RemotePost>
    suspend fun update(post: RemotePost): Resource<RemotePost>
    suspend fun delete(id: Int): Resource<Unit>
}
