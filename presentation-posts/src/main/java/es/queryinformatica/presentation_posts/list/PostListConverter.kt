package es.queryinformatica.presentation_posts.list

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.queryinformatica.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import es.queryinformatica.presentation_common.state.CommonResultConverter
import es.queryinformatica.presentation_posts.R
import javax.inject.Inject

class PostListConverter @Inject  constructor(
        @ApplicationContext private val context: Context
): CommonResultConverter<GetPostsWithUsersWithInteractionUseCase.Response, PostListModel>(){

        override fun convertSuccess(data: GetPostsWithUsersWithInteractionUseCase.Response): PostListModel =
                PostListModel(
                        headerText = context.getString(R.string.total_click_count, data.interaction.totalClicks),
                        posts = data.posts.map {
                                PostListItemModel(
                                        id = it.post.id,
                                        userId = it.post.userId,
                                        authorName = context.getString(R.string.author, it.user.name),
                                        title = context.getString(R.string.title, it.post.title)
                                )
                        }, interaction = data.interaction
                )

}