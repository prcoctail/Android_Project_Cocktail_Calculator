package ru.partyshaker.partyshaker.cocktails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.partyshaker.partyshaker.ApiResponse
import javax.inject.Inject
import ru.partyshaker.partyshaker.cocktails.data.data_classes.Result

@HiltViewModel
class ViewModelCocktails @Inject constructor(private val repository: RepositoryCocktails)  : ViewModel() {

    val cocktailsList = MutableLiveData<List<Result>?>()
    val errorMessage = MutableLiveData<String>()

    fun getCocktailsAPI() {
        viewModelScope.launch {
            val result = repository.getCocktails()
            when {
                result is ApiResponse.Success -> cocktailsList.postValue(result.data)
                result is ApiResponse.Error -> errorMessage.postValue(result.exception)
            }
        }

    }
}