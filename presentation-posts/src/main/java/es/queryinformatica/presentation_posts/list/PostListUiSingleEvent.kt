package es.queryinformatica.presentation_posts.list

import es.queryinformatica.presentation_common.UiSingleEvent

sealed class PostListUiSingleEvent : UiSingleEvent {
    data class OpenUserScreen(val navRoute:String) : PostListUiSingleEvent()
    data class OpenPostScreen(val navRoute:String) : PostListUiSingleEvent()
}