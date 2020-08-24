package br.com.mpc.experimentation.dispatchers

import br.com.mpc.experimentation.api.MarvelAPIRepository
import br.com.mpc.experimentation.models.charactersRequest.Results

class HomeDispatcher(private val repository: MarvelAPIRepository) {

    suspend fun getCharacters(): List<Results> {
        return repository.getCharacters().body()!!.data.results
    }
}