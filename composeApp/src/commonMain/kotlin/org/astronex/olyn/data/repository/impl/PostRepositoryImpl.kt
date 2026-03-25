package org.astronex.olyn.data.repository.impl

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.astronex.olyn.common.Resource
import org.astronex.olyn.data.remote.api.PostApi
import org.astronex.olyn.data.remote.dto.PostCreateDto
import org.astronex.olyn.data.remote.dto.PostDto
import org.astronex.olyn.data.remote.mapper.toRemotePost
import org.astronex.olyn.data.remote.util.safeApiCall
import org.astronex.olyn.domain.model.RemotePost
import org.astronex.olyn.domain.repository.PostRepository

/**
 * Default [PostRepository] backed by [PostApi]. Copy this pattern for other resources (swap API + DTO + mapper).
 */
class PostRepositoryImpl(
    private val ioDispatcher: CoroutineDispatcher,
    private val api: PostApi,
) : PostRepository {

    override suspend fun list(): Resource<List<RemotePost>> = withContext(ioDispatcher) {
        safeApiCall { api.listPosts().map { postDto -> postDto.toRemotePost() } }
    }

    override suspend fun getById(id: Int): Resource<RemotePost> = withContext(ioDispatcher) {
        safeApiCall { api.getPost(id).toRemotePost() }
    }

    override suspend fun create(title: String, body: String, userId: Int): Resource<RemotePost> =
        withContext(ioDispatcher) {
            safeApiCall {
                api.createPost(PostCreateDto(title = title, body = body, userId = userId)).toRemotePost()
            }
        }

    override suspend fun update(post: RemotePost): Resource<RemotePost> = withContext(ioDispatcher) {
        val id = post.id ?: return@withContext Resource.Error(message = "Post id required for update")
        safeApiCall {
            api.updatePost(
                id = id,
                post = PostDto(
                    id = id,
                    userId = post.userId,
                    title = post.title,
                    body = post.body,
                ),
            ).toRemotePost()
        }
    }

    override suspend fun delete(id: Int): Resource<Unit> = withContext(ioDispatcher) {
        safeApiCall {
            api.deletePost(id)
        }
    }
}
