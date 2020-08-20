package br.com.mpc.experimentation.api

import br.com.mpc.experimentation.models.charactersRequest.Characters
import retrofit2.Response
import retrofit2.http.GET

interface MarvelAPI {

    @GET("characters")
    suspend fun getCharacters(): Response<Characters>
}