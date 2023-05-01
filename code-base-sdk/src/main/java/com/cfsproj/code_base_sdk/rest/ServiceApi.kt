package com.cfsproj.code_base_sdk.rest

import com.cfsproj.code_base_sdk.model.CharacterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("/")
    suspend fun getCharacters(
        @Query("q") type: String,
        @Query("format") format: String = JSON_FORMAT
    ): Response<CharacterResponse>

    companion object{
        const val JSON_FORMAT = "json"
    }
}