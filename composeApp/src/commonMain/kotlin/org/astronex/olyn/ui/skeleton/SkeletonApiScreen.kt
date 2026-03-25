package org.astronex.olyn.ui.skeleton

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import compose_multiplatform.composeapp.generated.resources.Res
import compose_multiplatform.composeapp.generated.resources.api_skeleton_screen_title
import compose_multiplatform.composeapp.generated.resources.ic_back
import org.astronex.olyn.ui.component.CoreLayout
import org.astronex.olyn.ui.component.CoreTopBar
import org.astronex.olyn.ui.theme.customizedTextStyle
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SkeletonApiScreen(
    onBack: () -> Unit,
    viewModel: SkeletonApiViewModel = koinViewModel(),
) {
    val state by viewModel.uiState.collectAsState()

    CoreLayout(
        topBar = {
            CoreTopBar(
                title = stringResource(Res.string.api_skeleton_screen_title),
                titleTextStyle = customizedTextStyle(
                    fontSize = 16,
                    fontWeight = 500,
                    color = Color.Black,
                ),
                leftIcon = Res.drawable.ic_back,
                onClickLeft = onBack,
            )
        },
        modifier = Modifier.fillMaxSize(),
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "Ktor + kotlinx.serialization · JSONPlaceholder `/posts`",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    Button(
                        onClick = { viewModel.loadPosts() },
                        enabled = !state.loading,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("GET list")
                    }
                    Button(
                        onClick = { viewModel.createDemoPost() },
                        enabled = !state.loading,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("POST create")
                    }
                    Button(
                        onClick = { viewModel.updateFirstPost() },
                        enabled = !state.loading,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("PUT update first")
                    }
                    Button(
                        onClick = { viewModel.deleteFirstPost() },
                        enabled = !state.loading,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text("DELETE first")
                    }
                }

                state.statusMessage?.let { msg ->
                    Text(
                        text = msg,
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(vertical = 4.dp),
                    )
                }

                if (state.loading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(16.dp),
                    )
                }

                LazyColumn(
                    modifier = Modifier.weight(1f, fill = true),
                    contentPadding = PaddingValues(bottom = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(state.posts.take(20), key = { it.id ?: it.hashCode() }) { post ->
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Text(
                                text = "#${post.id ?: "?"} · ${post.title}",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.SemiBold,
                            )
                            Text(
                                text = post.body.take(120) + if (post.body.length > 120) "…" else "",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.DarkGray,
                            )
                        }
                    }
                }
            }
        },
    )
}
