package ru.partyshaker.partyshaker.ui.features.cocktails.api

import retrofit2.Response
import retrofit2.http.GET
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.CocktailsResponse

interface CocktailsService {

    @GET("cocktails/")
    suspend fun getAllCocktails(
    ): Response<CocktailsResponse>
}