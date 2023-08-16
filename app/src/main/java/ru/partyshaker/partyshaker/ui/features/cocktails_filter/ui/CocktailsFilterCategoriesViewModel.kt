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
class CocktailsFilterCategoriesViewModel @Inject constructor(
    private val repository: CocktailsFilterRepositoryImpl
) : ViewModel() {

    private var _complexities: MutableLiveData<MutableList<FilteredChip>> = MutableLiveData()
    val complexities: LiveData<MutableList<FilteredChip>>
        get() = _complexities

    private var _strengths: MutableLiveData<MutableList<FilteredChip>> = MutableLiveData()
    val strengths: LiveData<MutableList<FilteredChip>>
        get() = _strengths

    private var _tastes: MutableLiveData<MutableList<FilteredChip>> = MutableLiveData()
    val tastes: LiveData<MutableList<FilteredChip>>
        get() = _tastes

    private var _formats: MutableLiveData<MutableList<FilteredChip>> = MutableLiveData()
    val formats: LiveData<MutableList<FilteredChip>>
        get() = _formats

    init {
        getComplexities()
        getStrengths()
        getTastes()
        getFormats()
    }

    fun update(name: String, isChecked: Boolean, chipGroup: String) {
        when (chipGroup) {
            "complexities" -> {
                setComplexities(name, isChecked)
            }

            "strengths" -> {
                setStrengths(name, isChecked)
            }

            "tastes" -> {
                setTastes(name, isChecked)
            }

            "formats" -> {
                setFormats(name, isChecked)
            }
        }
    }

    private fun getComplexities() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getAllComplexities()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val list: MutableList<FilteredChip> = mutableListOf()

                        response.body()?.complexities?.forEach { complexity ->
                            list.add(FilteredChip(complexity.name, false))
                        }

                        _complexities.postValue(list)
                    }
                }
            }
        }
    }

    private fun getStrengths() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getAllStrengths()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val list: MutableList<FilteredChip> = mutableListOf()

                        response.body()?.strengths?.forEach { strength ->
                            list.add(FilteredChip(strength.name, false))
                        }

                        _strengths.postValue(list)
                    }
                }
            }
        }
    }

    private fun getTastes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getAllTastes()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val list: MutableList<FilteredChip> = mutableListOf()

                        response.body()?.tastes?.forEach { taste ->
                            list.add(FilteredChip(taste.name, false))
                        }

                        _tastes.postValue(list)
                    }
                }
            }
        }
    }

    private fun getFormats() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val response = repository.getAllFormats()

                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val list: MutableList<FilteredChip> = mutableListOf()

                        response.body()?.formats?.forEach { format ->
                            list.add(FilteredChip(format.name, false))
                        }

                        _formats.postValue(list)
                    }
                }
            }
        }
    }

    private fun setComplexities(name: String, isChecked: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val list: MutableList<FilteredChip> = _complexities.value ?: mutableListOf()
                list.find { it.name == name }?.isChecked = isChecked
                _complexities.postValue(list)
            }
        }
    }

    private fun setStrengths(name: String, isChecked: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val list: MutableList<FilteredChip> = _strengths.value ?: mutableListOf()
                list.find { it.name == name }?.isChecked = isChecked
                _strengths.postValue(list)
            }
        }
    }

    private fun setTastes(name: String, isChecked: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val list: MutableList<FilteredChip> = _tastes.value ?: mutableListOf()
                list.find { it.name == name }?.isChecked = isChecked
                _tastes.postValue(list)
            }
        }
    }

    private fun setFormats(name: String, isChecked: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                val list: MutableList<FilteredChip> = _formats.value ?: mutableListOf()
                list.find { it.name == name }?.isChecked = isChecked
                _formats.postValue(list)
            }
        }
    }
}