package ru.partyshaker.partyshaker.ui.features.cocktails_filter.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.partyshaker.partyshaker.ui.features.cocktails.data.repository.CocktailsFilterRepositoryImpl
import ru.partyshaker.partyshaker.ui.features.cocktails_filter.data.data_classes.FilteredChip
import javax.inject.Inject

@HiltViewModel
class CocktailsFilterIngredientsViewModel @Inject constructor(
    private val repository: CocktailsFilterRepositoryImpl
) : ViewModel() {

    private var _ingredients: MutableList<String> = mutableListOf()

    private var _filteredIngredients: MutableLiveData<MutableList<FilteredChip>> = MutableLiveData()
    val filteredIngredients: LiveData<MutableList<FilteredChip>>
        get() = _filteredIngredients

    init {
        getIngredients()
    }

    fun update(name: String, isChecked: Boolean, chipGroup: String) {
        when (chipGroup) {
            "ingredients" -> {
                setIngredients(name, isChecked)
            }
        }
    }

    private fun getFilteredIngredients(ingredients: MutableList<String>) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val list: MutableList<FilteredChip> = mutableListOf()

                ingredients.forEach { ingredient ->
                    list.add(FilteredChip(ingredient, false))
                }

                _filteredIngredients.postValue(list)
            }
        }
    }

    fun setIngredientsFilter(name: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val list: MutableList<String> = _ingredients
                val list2: MutableList<FilteredChip> = _filteredIngredients.value ?: mutableListOf()
                val result: MutableList<FilteredChip> = mutableListOf()

                list.forEach { ingredient ->
                    result.add(FilteredChip(ingredient, false))
                }

                list2.forEach { item ->
                    result.find { it.name == item.name }?.isChecked = item.isChecked
                }

                result.sortedWith(compareBy({ it.name }, { it.isChecked }))
                val test = result.filter {
                    it.name.lowercase().contains(name.lowercase()) || it.isChecked
                }
                _filteredIngredients.postValue(test as MutableList<FilteredChip>)
            }
        }
    }

    private fun getIngredients() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getAllIngredients()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val list: MutableList<String> = mutableListOf()

                        response.body()?.ingredients?.forEach { ingredient ->
                            list.add(ingredient.name)
                        }

                        list.sortBy { it }
                        _ingredients = list
                        getFilteredIngredients(_ingredients)
                    }
                }
            }
        }
    }

    private fun setIngredients(name: String, isChecked: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val list: MutableList<FilteredChip> = _filteredIngredients.value ?: mutableListOf()
                list.find { it.name == name }?.isChecked = isChecked
                _filteredIngredients.postValue(list)
            }
        }
    }
}