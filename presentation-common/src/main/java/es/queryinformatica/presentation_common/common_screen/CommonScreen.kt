package es.queryinformatica.presentation_common.common_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import es.queryinformatica.presentation_common.state.UiState

@Composable
fun <T: Any>CommonScreen(state: UiState<T>, onSuccess: @Composable (T) -> Unit){
    when(state){
        is UiState.Loading -> Loading()
        is UiState.Error -> Error(errorMessage = state.errorMessage)
        is UiState.Success -> onSuccess(state.data)
    }
}


@Composable
fun Error(errorMessage: String) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Snackbar{
            Text(text = errorMessage)
        }
    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}