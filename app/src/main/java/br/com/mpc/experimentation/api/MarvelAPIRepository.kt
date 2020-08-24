package br.com.mpc.experimentation.api

import br.com.mpc.experimentation.models.charactersRequest.Characters
import retrofit2.Response
import retrofit2.http.GET

interface MarvelAPIRepository {

    @GET("characters/{id}/")
    suspend fun getCharacter(id: String): Response<Characters>

    @GET("characters/")
    suspend fun getCharacters(): Response<Characters>
}