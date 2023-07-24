package es.queryinformatica.presentation_posts.single

import es.queryinformatica.presentation_common.UiAction

sealed class PostUiAction : UiAction {
    class Load(val postId: Long) : PostUiAction()
}