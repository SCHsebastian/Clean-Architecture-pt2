package es.queryinformatica.presentation_posts.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.queryinformatica.presentation_posts.PostListViewModel

@Composable
fun Error(errorMessage: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
       Snackbar{
              Text(text = errorMessage)
       }
    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun PostList(
    postListModel: PostListModel
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        item(postListModel.headerText) {
            Text(text = postListModel.headerText)
        }
        items(postListModel.posts) {item ->
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = item.authorName)
                Text(text = item.title)
            }
        }
    }
}

@Composable
fun PostListScreen(
    viewmodel: PostListViewModel
) {
    viewmodel.loadPosts()
    viewmodel.postListFlow.collectAsState().value.let {
        when (it) {
            is UiState.Loading -> Loading()
            is UiState.Error -> Error(it.errorMessage)
            is UiState.Success -> PostList(it.data)
        }
    }
}