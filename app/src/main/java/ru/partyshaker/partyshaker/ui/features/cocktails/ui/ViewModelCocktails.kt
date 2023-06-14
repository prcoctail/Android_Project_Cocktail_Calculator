package ru.partyshaker.partyshaker.ui.features.cocktails.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ViewModelCocktails  : ViewModel() {

    val cocktailsList =
        MutableLiveData<List<ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail>?>()
    val errorMessage = MutableLiveData<String>()

    fun getCocktailsAPI() {
        viewModelScope.launch {
            try {

            } catch (e: Exception) {
                // TODO(): Toast
            }
        }

    }
}