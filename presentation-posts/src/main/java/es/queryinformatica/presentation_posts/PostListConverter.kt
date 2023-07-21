package es.queryinformatica.presentation_posts

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.queryinformatica.domain.entity.Result
import es.queryinformatica.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import es.queryinformatica.presentation_posts.list.PostListItemModel
import es.queryinformatica.presentation_posts.list.PostListModel
import es.queryinformatica.presentation_posts.list.UiState
import javax.inject.Inject

class PostListConverter @Inject  constructor(
        @ApplicationContext private val context: Context,
){
    fun convert(postListResult: Result<GetPostsWithUsersWithInteractionUseCase.Response>): UiState<PostListModel> {
            return when (postListResult){
                    is Result.Error -> UiState.Error(postListResult.exception.localizedMessage.orEmpty())
                    is Result.Success -> UiState.Success(PostListModel(
                            headerText = context.getString(R.string.total_click_count, postListResult.data.interaction.totalClicks),
                            posts = postListResult.data.posts.map {
                                    PostListItemModel(
                                            id = it.post.id,
                                            userId = it.post.userId,
                                            authorName = context.getString(R.string.author, it.user.name),
                                            title = context.getString(R.string.title, it.post.title)
                                    )
                            }
                    ))
            }
    }
}