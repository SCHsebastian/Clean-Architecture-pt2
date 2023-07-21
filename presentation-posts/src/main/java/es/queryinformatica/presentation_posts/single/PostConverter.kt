package es.queryinformatica.presentation_posts.single

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.queryinformatica.domain.usecase.GetPostUseCase
import es.queryinformatica.presentation_common.state.CommonResultConverter
import es.queryinformatica.presentation_posts.R
import javax.inject.Inject

class PostConverter @Inject constructor(
    @ApplicationContext private val context: Context,
) : CommonResultConverter<GetPostUseCase.Response, PostModel>() {
    override fun convertSuccess(data: GetPostUseCase.Response): PostModel {
        return PostModel(
            title = context.getString(R.string.title, data.post.title),
            body = context.getString(R.string.body, data.post.body)
        )
    }
}