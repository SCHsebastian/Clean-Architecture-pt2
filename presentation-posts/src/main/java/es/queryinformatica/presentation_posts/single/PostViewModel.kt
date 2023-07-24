package es.queryinformatica.presentation_posts.single

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.queryinformatica.domain.usecase.GetPostUseCase
import es.queryinformatica.presentation_common.MviViewModel
import es.queryinformatica.presentation_common.state.UiState
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val useCase: GetPostUseCase,
    private val converter: PostConverter,
) : MviViewModel<PostModel, UiState<PostModel>, PostUiAction, PostUiSingleEvent>(){

    override fun initState(): UiState<PostModel> = UiState.Loading
    override fun handleAction(action: PostUiAction) {
        when(action){
            is PostUiAction.Load -> loadPost(action.postId)
        }
    }

    private fun loadPost(postId: Long){
        viewModelScope.launch {
            useCase.execute(
                GetPostUseCase.Request(postId)
            ).map {
                converter.convert(it)
            }.collect {
                submitState(it)
            }
        }
    }

}