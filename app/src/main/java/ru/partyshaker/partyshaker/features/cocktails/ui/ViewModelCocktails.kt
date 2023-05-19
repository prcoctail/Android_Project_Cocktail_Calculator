package ru.partyshaker.partyshaker.features.cocktails.ui

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.partyshaker.partyshaker.feature_utils.entity.Result
import javax.inject.Inject
import ru.partyshaker.partyshaker.features.cocktails.data.data_classes.Cocktail
import ru.partyshaker.partyshaker.features.cocktails.repository.RepositoryCocktails

@HiltViewModel
class ViewModelCocktails @Inject constructor(private val repository: RepositoryCocktails)  : ViewModel() {

    val cocktailsList = MutableLiveData<List<Cocktail>?>()
    val errorMessage = MutableLiveData<String>()

    fun getCocktailsAPI() {
        viewModelScope.launch {
            try {
                val result = repository.getCocktails()
                when (result) {
                    is Result.Success -> cocktailsList.postValue(result.data)
                    is Result.Error -> errorMessage.postValue(result.exception)
                }
            } catch (e: Exception) {
             // TODO(): Toast
            }
        }

    }
}