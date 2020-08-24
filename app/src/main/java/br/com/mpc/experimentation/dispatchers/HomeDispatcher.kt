package br.com.mpc.experimentation.dispatchers

import br.com.mpc.experimentation.api.MarvelAPIRepository
import br.com.mpc.experimentation.baseComponents.BaseDispatcher
import br.com.mpc.experimentation.baseComponents.TreatedResponse
import br.com.mpc.experimentation.models.charactersRequest.Characters
import br.com.mpc.experimentation.ui.home.HomeState

class HomeDispatcher(private val repository: MarvelAPIRepository) : BaseDispatcher<HomeState>() {

    fun getCharacters(): TreatedResponse<Characters> {
        return doRequest { repository.getCharacters() }
    }
}