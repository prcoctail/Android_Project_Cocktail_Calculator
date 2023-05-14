package ru.partyshaker.partyshaker.cocktails

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.partyshaker.partyshaker.ApiResponse
import ru.partyshaker.partyshaker.cocktails.data.ServiceCocktails
import javax.inject.Inject


class RepositoryCocktails @Inject constructor(
    private val retrofitService: ServiceCocktails
) {
    suspend fun getCocktails() : ApiResponse<List<ru.partyshaker.partyshaker.cocktails.data.data_classes.Result>> {
        return withContext(Dispatchers.Default){
            val data = retrofitService.getCocktails()
            if(data.isSuccessful){
                return@withContext ApiResponse.Success(data.body()!!.results)
            } else {
                return@withContext ApiResponse.Error(data.message())
            }
        }
    }
}