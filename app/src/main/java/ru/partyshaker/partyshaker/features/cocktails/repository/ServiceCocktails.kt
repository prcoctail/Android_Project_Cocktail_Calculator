package ru.partyshaker.partyshaker.features.cocktails.repository


import retrofit2.Response
import retrofit2.http.GET
import ru.partyshaker.partyshaker.features.cocktails.data.data_classes.CocktailResponse


interface ServiceCocktails  {
    @GET ("cocktails/")
    suspend fun getCocktails(): Response<CocktailResponse>
}