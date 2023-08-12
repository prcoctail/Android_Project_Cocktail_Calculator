package ru.partyshaker.partyshaker.ui.features.cocktails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.CocktailsResponse
import ru.partyshaker.partyshaker.ui.features.cocktails.data.repository.CocktailsRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class CocktailsViewModel @Inject constructor(
    private val repository: CocktailsRepositoryImpl
) : ViewModel() {
    private val cocktailsResponse: LiveData<CocktailsResponse>
        get() = repository.responseList

    init {
        viewModelScope.launch {
            repository.getAllCocktails()
        }
    }

    fun getAllCocktails(): LiveData<CocktailsResponse> {
        return cocktailsResponse
    }
}