package es.queryinformatica.presentation_user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import es.queryinformatica.presentation_common.common_screen.CommonScreen
import es.queryinformatica.presentation_common.navigation.UserInput

@Composable
fun UserScreen(
    viewModel: UserViewModel,
    userInput: UserInput
) {
    viewModel.loadUser(userInput.userId)
    viewModel.userFlow.collectAsState().value.let { state ->
        CommonScreen(state = state) { user ->
            User(user = user)
        }
    }
}

@Composable
fun User(
    user: UserModel
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = user.name)
        Text(text = user.username)
        Text(text = user.email)
    }
}