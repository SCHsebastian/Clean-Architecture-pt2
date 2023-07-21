package es.queryinformatica.presentation_posts.single

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.queryinformatica.domain.usecase.GetPostUseCase
import es.queryinformatica.presentation_common.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val useCase: GetPostUseCase,
    private val converter: PostConverter,
) : ViewModel(){

    private val _postFlow = MutableStateFlow<UiState<PostModel>>(UiState.Loading)
    val postFlow: MutableStateFlow<UiState<PostModel>> = _postFlow

    fun loadPost(postId: Long){
        viewModelScope.launch {
            useCase.execute(
                GetPostUseCase.Request(postId)
            ).map {
                converter.convert(it)
            }.collect {
                _postFlow.value = it
            }
        }
    }

}