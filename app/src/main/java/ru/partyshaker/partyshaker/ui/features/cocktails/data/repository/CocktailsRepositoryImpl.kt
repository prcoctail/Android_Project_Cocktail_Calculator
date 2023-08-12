package ru.partyshaker.partyshaker.ui.features.cocktails.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.partyshaker.partyshaker.ui.features.cocktails.api.CocktailsService
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.CocktailsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CocktailsRepositoryImpl @Inject constructor(
    private val service: CocktailsService,
) : CocktailsRepository {

    private val _response = MutableLiveData<CocktailsResponse>()
    val responseList: LiveData<CocktailsResponse>
        get() = _response

    override suspend fun getAllCocktails() {
        val result = service.getAllCocktails()

        if (result.isSuccessful && result.body() != null) {
            _response.postValue(result.body())
        }
    }
}