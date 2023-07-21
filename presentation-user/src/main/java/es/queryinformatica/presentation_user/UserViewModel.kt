package es.queryinformatica.presentation_user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.queryinformatica.domain.usecase.GetUserUseCase
import es.queryinformatica.presentation_common.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCase: GetUserUseCase,
    private val converter: UserConverter,
): ViewModel() {

    private val _userFlow = MutableStateFlow<UiState<UserModel>>(UiState.Loading)
    val userFlow: MutableStateFlow<UiState<UserModel>> = _userFlow

    fun loadUser(userId: Long) {
        viewModelScope.launch {
            useCase.execute(
                GetUserUseCase.Request(userId)
            ).map {
                converter.convert(it)
            }.collect {
                _userFlow.value = it
            }
        }
    }
}