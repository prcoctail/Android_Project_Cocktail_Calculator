package ru.partyshaker.partyshaker.cocktails

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.partyshaker.partyshaker.ApiResponse
import javax.inject.Inject
import ru.partyshaker.partyshaker.cocktails.data.data_classes.Result

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