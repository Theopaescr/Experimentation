package br.com.mpc.experimentation.ui.home

import androidx.lifecycle.*
import br.com.mpc.experimentation.baseComponents.BaseViewModel
import br.com.mpc.experimentation.dispatchers.HomeDispatcher
import br.com.mpc.experimentation.models.charactersRequest.Characters

class HomeViewModel(private val handle: SavedStateHandle, private val dispatcher: HomeDispatcher) : BaseViewModel<HomeState>(dispatcher) {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> get() = _isLoading

    private val _message = MutableLiveData<String>()
    val message : LiveData<String> get() = _message

    private val _listOfCharacters = MutableLiveData<Characters>()
    val listOfCharacters : LiveData<Characters> get() = _listOfCharacters

    fun getCharacters() {
        _isLoading.value = true

        val result = dispatcher.getCharacters()
        if (result.isSuccess) {
            _listOfCharacters.value = result.response
            _isLoading.value = false

        } else {
            _message.value = result.errorMessage
            _isLoading.value = false
        }
    }
}