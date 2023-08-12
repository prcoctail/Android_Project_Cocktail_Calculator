package ru.partyshaker.partyshaker.ui.features.cocktails.data.repository

import retrofit2.Response
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.ComplexitiesResponse
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.FormatsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.IngredientsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.StrengthsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.filter.TastesResponse

interface CocktailsFilterRepository {
    suspend fun getAllComplexities(): Response<ComplexitiesResponse>

    suspend fun getAllStrengths(): Response<StrengthsResponse>

    suspend fun getAllTastes(): Response<TastesResponse>

    suspend fun getAllFormats(): Response<FormatsResponse>

    suspend fun getAllIngredients(): Response<IngredientsResponse>

    suspend fun getFilteredComplexities(name: String): Response<ComplexitiesResponse>
}