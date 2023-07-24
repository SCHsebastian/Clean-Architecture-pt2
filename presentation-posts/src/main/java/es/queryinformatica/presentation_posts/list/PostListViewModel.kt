package es.queryinformatica.presentation_posts.list

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.queryinformatica.domain.entity.Interaction
import es.queryinformatica.domain.usecase.GetPostsWithUsersWithInteractionUseCase
import es.queryinformatica.domain.usecase.UpdateInteractionUseCase
import es.queryinformatica.presentation_common.MviViewModel
import es.queryinformatica.presentation_common.navigation.NavRoutes
import es.queryinformatica.presentation_common.navigation.PostInput
import es.queryinformatica.presentation_common.navigation.UserInput
import es.queryinformatica.presentation_common.state.UiState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostListViewModel @Inject constructor(
    private val useCase: GetPostsWithUsersWithInteractionUseCase,
    private val converter: PostListConverter,
    private val updateInteraction: UpdateInteractionUseCase
) : MviViewModel<PostListModel, UiState<PostListModel>, PostListUiAction, PostListUiSingleEvent>() {

    override fun initState(): UiState<PostListModel> = UiState.Loading

    private fun loadPosts() {
        viewModelScope.launch {
            useCase.execute(GetPostsWithUsersWithInteractionUseCase.Request)
                .map {
                    converter.convert(it)
                }.collect {
                    submitState(it)
                }
        }

    }

    private fun updateInteraction(interaction: Interaction) {
        viewModelScope.launch {
            updateInteraction.execute(
                UpdateInteractionUseCase.Request(
                    interaction.copy(totalClicks = interaction.totalClicks + 1)
                )
            ).collect()
        }
    }

    override fun handleAction(action: PostListUiAction) {
        when (action){
            PostListUiAction.Load -> loadPosts()
            is PostListUiAction.PostClick -> {
                updateInteraction(action.interaction)
                submitSingleEvent(
                    PostListUiSingleEvent.OpenPostScreen(
                        NavRoutes.Post.routeForPost(
                            PostInput(
                                action.postId
                            )
                        )
                    )
                )
            }
            is PostListUiAction.UserClick -> {
                updateInteraction(action.interaction)
                submitSingleEvent(
                    PostListUiSingleEvent.OpenUserScreen(
                        NavRoutes.User.routeForUser(
                            UserInput(
                                action.userId
                            )
                        )
                    )
                )
            }
        }
    }

}