package ru.partyshaker.partyshaker.ui.features.cocktails.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import ru.partyshaker.partyshaker.di.CocktailsService
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.CocktailsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailsRepositoryImpl @Inject constructor(
    private val service: CocktailsService,
) : CocktailsRepository {
    override suspend fun getAllCocktails(): Response<CocktailsResponse> =
        withContext(Dispatchers.IO) {
            service.getAllCocktails()
        }
}