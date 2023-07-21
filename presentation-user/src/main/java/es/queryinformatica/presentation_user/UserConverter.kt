package es.queryinformatica.presentation_user

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import es.queryinformatica.domain.usecase.GetUserUseCase
import es.queryinformatica.presentation_common.state.CommonResultConverter
import javax.inject.Inject

class UserConverter @Inject constructor(
    @ApplicationContext private val context: Context,
) : CommonResultConverter<GetUserUseCase.Response, UserModel>() {
    override fun convertSuccess(data: GetUserUseCase.Response): UserModel {
        return UserModel(
            name = context.getString(R.string.name, data.user.name),
            username = context.getString(R.string.username, data.user.username),
            email = context.getString(R.string.email, data.user.email)
        )
    }
}