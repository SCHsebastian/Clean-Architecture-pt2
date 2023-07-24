package es.queryinformatica.presentation_user

import es.queryinformatica.presentation_common.UiAction

sealed class UserUiAction : UiAction{
    class Load(val userId: Long) : UserUiAction()
}