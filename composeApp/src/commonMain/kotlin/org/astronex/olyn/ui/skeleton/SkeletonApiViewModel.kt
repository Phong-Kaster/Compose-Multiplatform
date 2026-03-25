package org.astronex.olyn.ui.skeleton

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.astronex.olyn.common.Resource
import org.astronex.olyn.domain.model.RemotePost
import org.astronex.olyn.domain.repository.PostRepository

/**
 * Demo screen logic: drives GET/POST/PUT/DELETE through [PostRepository] and mirrors results in
 * [SkeletonApiUiState]. Use it as a reference when wiring new ViewModels to [Resource]-based repos.
 */
data class SkeletonApiUiState(
    val loading: Boolean = false,
    val posts: List<RemotePost> = emptyList(),
    val statusMessage: String? = null,
)

class SkeletonApiViewModel(
    private val postRepository: PostRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SkeletonApiUiState())
    val uiState: StateFlow<SkeletonApiUiState> = _uiState.asStateFlow()

    init {
        loadPosts()
    }

    fun dismissMessage() {
        _uiState.value = _uiState.value.copy(statusMessage = null)
    }

    fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, statusMessage = null)
            when (val result = postRepository.list()) {
                is Resource.Success ->
                    _uiState.value = _uiState.value.copy(loading = false, posts = result.data)

                is Resource.Error ->
                    _uiState.value = _uiState.value.copy(loading = false, statusMessage = result.message)

                is Resource.Loading -> { /* not emitted by repository suspend APIs */ }
            }
        }
    }

    fun createDemoPost() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, statusMessage = null)
            when (
                val result = postRepository.create(
                    title = "KMP skeleton",
                    body = "Created from Compose Multiplatform template",
                    userId = 1,
                )
            ) {
                is Resource.Success ->
                    _uiState.value = _uiState.value.copy(
                        loading = false,
                        statusMessage = "Created post id=${result.data.id} (mock server may return fake id)",
                    )

                is Resource.Error ->
                    _uiState.value = _uiState.value.copy(loading = false, statusMessage = result.message)

                is Resource.Loading -> { }
            }
            loadPosts()
        }
    }

    fun updateFirstPost() {
        val first = _uiState.value.posts.firstOrNull()
        if (first == null) {
            _uiState.value = _uiState.value.copy(statusMessage = "Load posts first")
            return
        }
        val id = first.id
        if (id == null) {
            _uiState.value = _uiState.value.copy(statusMessage = "First item has no id")
            return
        }
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, statusMessage = null)
            val updated = first.copy(title = first.title + " · updated")
            when (val result = postRepository.update(updated)) {
                is Resource.Success ->
                    _uiState.value = _uiState.value.copy(loading = false, statusMessage = "Updated post $id (mock)")

                is Resource.Error ->
                    _uiState.value = _uiState.value.copy(loading = false, statusMessage = result.message)

                is Resource.Loading -> { }
            }
            loadPosts()
        }
    }

    fun deleteFirstPost() {
        val first = _uiState.value.posts.firstOrNull()
        if (first == null) {
            _uiState.value = _uiState.value.copy(statusMessage = "Load posts first")
            return
        }
        val id = first.id
        if (id == null) {
            _uiState.value = _uiState.value.copy(statusMessage = "First item has no id")
            return
        }
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(loading = true, statusMessage = null)
            when (val result = postRepository.delete(id)) {
                is Resource.Success ->
                    _uiState.value = _uiState.value.copy(loading = false, statusMessage = "Deleted post $id (mock)")

                is Resource.Error ->
                    _uiState.value = _uiState.value.copy(loading = false, statusMessage = result.message)

                is Resource.Loading -> { }
            }
            loadPosts()
        }
    }
}
