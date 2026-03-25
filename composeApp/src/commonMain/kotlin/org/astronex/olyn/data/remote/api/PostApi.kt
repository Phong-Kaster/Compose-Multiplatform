package org.astronex.olyn.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import org.astronex.olyn.data.remote.dto.PostCreateDto
import org.astronex.olyn.data.remote.dto.PostDto

/**
 * HTTP calls for the `posts` resource only. No business rules — keep validation and mapping in
 * [org.astronex.olyn.data.repository.impl.PostRepositoryImpl].
 */
class PostApi(private val client: HttpClient) {

    suspend fun listPosts(): List<PostDto> = client.get(ApiPath.POSTS).body()

    suspend fun getPost(id: Int): PostDto = client.get(ApiPath.post(id)).body()

    suspend fun createPost(body: PostCreateDto): PostDto =
        client.post(ApiPath.POSTS) { setBody(body) }.body()

    suspend fun updatePost(id: Int, post: PostDto): PostDto =
        client.put(ApiPath.post(id)) { setBody(post.copy(id = id)) }.body()

    suspend fun deletePost(id: Int) {
        client.delete(ApiPath.post(id))
    }
}
