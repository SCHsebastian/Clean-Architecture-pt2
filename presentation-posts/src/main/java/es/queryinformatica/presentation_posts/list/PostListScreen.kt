package es.queryinformatica.presentation_posts.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import es.queryinformatica.presentation_common.common_screen.CommonScreen
import es.queryinformatica.presentation_common.navigation.NavRoutes
import es.queryinformatica.presentation_common.navigation.PostInput
import es.queryinformatica.presentation_common.navigation.UserInput

@Composable
fun PostListScreen(
    viewmodel: PostListViewModel,
    navController: NavController
) {
    viewmodel.loadPosts()
    viewmodel.postListFlow.collectAsState().value.let {state ->
        CommonScreen(state = state){ postList ->
            PostList(postListModel = postList, {
                viewmodel.updateInteraction(postList.interaction)
                navController.navigate(NavRoutes.Post.routeForPost(PostInput(it.id)))
            }, {
                viewmodel.updateInteraction(postList.interaction)
                navController.navigate(NavRoutes.User.routeForUser(UserInput(it.userId)))
            })
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