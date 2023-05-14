package ru.partyshaker.partyshaker.features.cocktails.repository

import ru.partyshaker.partyshaker.feature_utils.entity.Result
import ru.partyshaker.partyshaker.features.cocktails.data.data_classes.Cocktail
import javax.inject.Inject


class RepositoryCocktails @Inject constructor(
    private val retrofitService: ServiceCocktails
) {
    suspend fun getCocktails(): Result<List<Cocktail>> {
        val data = retrofitService.getCocktails()
        if (data.isSuccessful) {
            return Result.Success(data.body()?.cocktails ?: emptyList())
        } else {
            return Result.Error(data.message())
        }
    }
}