package ru.partyshaker.partyshaker.ui.features.cocktails.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.ComplexitiesResponse
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.FormatsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.IngredientsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.StrengthsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.TastesResponse

interface CocktailsService {

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