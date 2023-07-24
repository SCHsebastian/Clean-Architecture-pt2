package es.queryinformatica.presentation_posts.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import es.queryinformatica.presentation_common.common_screen.CommonScreen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostListScreen(
    viewmodel: PostListViewModel,
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewmodel.submitAction(PostListUiAction.Load)
    }
    viewmodel.uiStateFlow.collectAsState().value.let {state ->
        CommonScreen(state = state){ postList ->
            PostList(postListModel = postList, {postListItem ->
                viewmodel.submitAction(
                    PostListUiAction.PostClick(
                        postListItem.id, postList.interaction
                    )
                )
            }, {
                postListItem ->
                viewmodel.submitAction(
                    PostListUiAction.UserClick(
                        postListItem.userId, postList.interaction
                    )
                )
            })
        }
    }

    LaunchedEffect(Unit){
        viewmodel.singleEventFlow.collectLatest {
            when(it){
                is PostListUiSingleEvent.OpenPostScreen -> {
                    navController.navigate(it.navRoute)
                }
                is PostListUiSingleEvent.OpenUserScreen -> {
                    navController.navigate(it.navRoute)
                }
            }
        }
    }
}


@Composable
fun PostList(
    postListModel: PostListModel,
    onRowClick: (PostListItemModel) -> Unit,
    onAuthorClick: (PostListItemModel) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item(postListModel.headerText) {
            Text(text = postListModel.headerText)
        }
        items(postListModel.posts) {item ->
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onRowClick(item)
                    }
            ) {
                ClickableText(
                    text = AnnotatedString(item.authorName),
                    onClick = { onAuthorClick(item) }
                )
                Text(text = item.title)
            }
        }
    }
}