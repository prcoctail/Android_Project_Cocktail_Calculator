package ru.partyshaker.partyshaker.ui.features.cocktails.repository

import retrofit2.Response
import retrofit2.http.GET
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.CocktailResponse

interface ServiceCocktails  {
    @GET ("cocktails/")
    suspend fun getCocktails(): Response<ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.CocktailResponse>
}