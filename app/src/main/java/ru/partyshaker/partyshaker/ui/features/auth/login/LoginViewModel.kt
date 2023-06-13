package ru.partyshaker.partyshaker.ui.features.auth.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.partyshaker.partyshaker.data.emptyError
import ru.partyshaker.partyshaker.data.partyShaker.users.UsersRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(val repo: UsersRepository) : ViewModel() {

    val loading = MutableLiveData(false)
    val loginError = MutableLiveData(emptyError)

    fun login(email: String, password: String) {
        if (loading.value == true) return
        viewModelScope.launch {
            loading.postValue(true)

        }
    }
}