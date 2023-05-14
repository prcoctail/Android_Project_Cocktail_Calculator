package ru.partyshaker.partyshaker.cocktails.data


import retrofit2.Response
import retrofit2.http.GET
import ru.partyshaker.partyshaker.cocktails.data.data_classes.CocktailResponse


interface ServiceCocktails  {
    @GET ("cocktails/")
    suspend fun getCocktails(): Response<CocktailResponse>
}