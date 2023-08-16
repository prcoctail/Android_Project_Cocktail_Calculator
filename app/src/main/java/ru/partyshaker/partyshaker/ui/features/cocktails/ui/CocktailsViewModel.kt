package ru.partyshaker.partyshaker.ui.features.cocktails.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail
import ru.partyshaker.partyshaker.ui.features.cocktails.data.repository.CocktailsRepositoryImpl
import javax.inject.Inject

@HiltViewModel
class CocktailsViewModel @Inject constructor(
    private val repository: CocktailsRepositoryImpl
) : ViewModel() {
    private var _cocktails: MutableLiveData<MutableList<Cocktail>> = MutableLiveData()
    val cocktails: LiveData<MutableList<Cocktail>>
        get() = _cocktails

    init {
        getCocktails()
    }

    private fun getCocktails() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getAllCocktails()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val list: MutableList<Cocktail> = mutableListOf()

                        response.body()?.cocktails?.forEach { cocktail ->
                            list.add(cocktail)
                        }

                        _cocktails.postValue(list)
                    }
                }
            }
        }
    }
}