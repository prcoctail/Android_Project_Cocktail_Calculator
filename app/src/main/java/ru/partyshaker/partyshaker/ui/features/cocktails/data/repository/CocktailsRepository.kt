package ru.partyshaker.partyshaker.ui.features.cocktails.data.repository;

import retrofit2.Response
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.CocktailsResponse

interface CocktailsRepository {
    suspend fun getAllCocktails(): Response<CocktailsResponse>
}