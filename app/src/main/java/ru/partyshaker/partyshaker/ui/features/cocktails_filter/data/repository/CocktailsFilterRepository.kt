package ru.partyshaker.partyshaker.ui.features.cocktails.data.repository

import retrofit2.Response
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.ComplexitiesResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.FormatsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.IngredientsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.StrengthsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.TastesResponse

interface CocktailsFilterRepository {
    suspend fun getAllComplexities(): Response<ComplexitiesResponse>

    suspend fun getAllStrengths(): Response<StrengthsResponse>

    suspend fun getAllTastes(): Response<TastesResponse>

    suspend fun getAllFormats(): Response<FormatsResponse>

    suspend fun getAllIngredients(): Response<IngredientsResponse>

    suspend fun getFilteredComplexities(name: String): Response<ComplexitiesResponse>
}