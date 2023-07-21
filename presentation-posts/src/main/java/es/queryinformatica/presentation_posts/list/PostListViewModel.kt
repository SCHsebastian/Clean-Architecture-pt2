package es.queryinformatica.presentation_posts.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.queryinformatica.domain.entity.Interaction
import es.queryinformatica.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import es.queryinformatica.domain.usecase.UpdateInteractionUseCase
import es.queryinformatica.presentation_common.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val useCase: GetPostsWithUsersWithInteractionUseCase,
    private val converter: PostListConverter,
    private val updateInteraction: UpdateInteractionUseCase
) : ViewModel() {

    private val _postListFlow = MutableStateFlow<UiState<PostListModel>>(UiState.Loading)
    val postListFlow: StateFlow<UiState<PostListModel>> = _postListFlow

    fun loadPosts() {
        viewModelScope.launch {
            useCase.execute(GetPostsWithUsersWithInteractionUseCase.Request)
                .map {
                    converter.convert(it)
                }.collect {
                    _postListFlow.value = it
                }
        }

    }

    fun updateInteraction(interaction: Interaction) {
        viewModelScope.launch {
            updateInteraction.execute(UpdateInteractionUseCase.Request(
                interaction.copy(totalClicks = interaction.totalClicks + 1)
            ))
        }
    }

}