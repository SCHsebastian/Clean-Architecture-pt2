package es.queryinformatica.presentation_posts.single

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.queryinformatica.presentation_common.common_screen.CommonScreen
import es.queryinformatica.presentation_common.navigation.PostInput

@Composable
fun PostScreen(
    viewModel: PostViewModel,
    postInput: PostInput
) {
    LaunchedEffect(Unit){
        viewModel.submitAction(PostUiAction.Load(postInput.postId))
    }
    viewModel.uiStateFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) { post ->
            Post(post = post)
        }
    }
}

@Composable
fun Post(
    post: PostModel
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = post.title)
        Text(text = post.body)
    }
}