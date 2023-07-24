package es.queryinformatica.presentation_user

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.queryinformatica.domain.usecase.GetUserUseCase
import es.queryinformatica.presentation_common.MviViewModel
import es.queryinformatica.presentation_common.state.UiState
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCase: GetUserUseCase,
    private val converter: UserConverter,
): MviViewModel<UserModel, UiState<UserModel>, UserUiAction, UserUiSingleEvent>() {

    override fun initState(): UiState<UserModel> = UiState.Loading

    override fun handleAction(action: UserUiAction) {
        when(action){
            is UserUiAction.Load -> loadUser(action.userId)
        }
    }

    private fun loadUser(userId: Long) {
        viewModelScope.launch {
            useCase.execute(
                GetUserUseCase.Request(userId)
            ).map {
                converter.convert(it)
            }.collect {
                submitState(it)
            }
        }
    }

}