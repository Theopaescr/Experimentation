package br.com.mpc.experimentation.ui.home

import br.com.mpc.experimentation.models.charactersRequest.Characters

data class HomeScreenState(
    val errorMessage: String,
    val content: Characters,
    val isLoading: Boolean,
    val state: HomeState
)


sealed class HomeState {
    object Loading: HomeState()
    data class Error(val message: String): HomeState()
    data class Success(val response: Characters): HomeState()
}