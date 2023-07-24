package es.queryinformatica.presentation_posts.list

import es.queryinformatica.domain.entity.Interaction
import es.queryinformatica.presentation_common.UiAction

sealed class PostListUiAction : UiAction {
    object Load : PostListUiAction()
    data class UserClick(val userId: Long, val interaction: Interaction) : PostListUiAction()
    data class PostClick(val postId: Long, val interaction: Interaction) : PostListUiAction()
}