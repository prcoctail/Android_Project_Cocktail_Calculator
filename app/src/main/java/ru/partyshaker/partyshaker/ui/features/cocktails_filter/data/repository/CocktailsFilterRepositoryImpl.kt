package ru.partyshaker.partyshaker.ui.features.cocktails.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.partyshaker.partyshaker.di.CocktailsService
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.ComplexitiesResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.FormatsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.IngredientsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.StrengthsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.TastesResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailsFilterRepositoryImpl @Inject constructor(
    private val service: CocktailsService
) : CocktailsFilterRepository {
    override suspend fun getAllComplexities(): Response<ComplexitiesResponse> =
        withContext(Dispatchers.IO) {
            service.getAllComplexities()
        }

    override suspend fun getAllStrengths(): Response<StrengthsResponse> =
        withContext(Dispatchers.IO) {
            service.getAllStrengths()
        }

    override suspend fun getAllTastes(): Response<TastesResponse> =
        withContext(Dispatchers.IO) {
            service.getAllTastes()
        }

    override suspend fun getAllFormats(): Response<FormatsResponse> =
        withContext(Dispatchers.IO) {
            service.getAllFormats()
        }

    override suspend fun getAllIngredients(): Response<IngredientsResponse> =
        withContext(Dispatchers.IO) {
            service.getAllIngredients()
        }

    override suspend fun getFilteredComplexities(name: String): Response<ComplexitiesResponse> =
        withContext(Dispatchers.IO) {
            service.getFilteredComplexities(name)
        }
}