package org.astronex.olyn.data.remote.mapper

import org.astronex.olyn.data.remote.dto.PostDto
import org.astronex.olyn.domain.model.RemotePost

fun PostDto.toRemotePost(): RemotePost = RemotePost(
    id = id,
    userId = userId,
    title = title,
    body = body,
)
