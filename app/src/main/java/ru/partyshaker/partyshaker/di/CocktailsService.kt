package ru.partyshaker.partyshaker.di

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.CocktailsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.ComplexitiesResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.FormatsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.IngredientsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.StrengthsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.TastesResponse

interface CocktailsService {

    @GET("cocktails/")
    suspend fun getAllCocktails(
    ): Response<CocktailsResponse>

    @GET("cocktails/complexities")
    suspend fun getAllComplexities(
    ): Response<ComplexitiesResponse>

    @GET("cocktails/strengths")
    suspend fun getAllStrengths(
    ): Response<StrengthsResponse>

    @GET("tastes")
    suspend fun getAllTastes(
    ): Response<TastesResponse>

    @GET("cocktails/formats")
    suspend fun getAllFormats(
    ): Response<FormatsResponse>

    @GET("ingredients")
    suspend fun getAllIngredients(
    ): Response<IngredientsResponse>

    @GET("cocktails/complexities")
    suspend fun getFilteredComplexities(
        @Query("name") name: String
    ): Response<ComplexitiesResponse>
}