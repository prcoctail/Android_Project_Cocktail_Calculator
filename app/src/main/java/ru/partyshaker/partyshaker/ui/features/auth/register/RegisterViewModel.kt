package ru.partyshaker.partyshaker.ui.features.auth.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.partyshaker.partyshaker.data.emptyError
import ru.partyshaker.partyshaker.data.partyShaker.users.UsersRepository
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    val usersRepository: UsersRepository
) : ViewModel() {

    val loading = MutableLiveData(false)
    val error = MutableLiveData(emptyError)

}