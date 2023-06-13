package ru.partyshaker.partyshaker.data.partyShaker.users.config

import android.content.Context
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import ru.partyshaker.partyshaker.data.partyShaker.users.User
import ru.partyshaker.partyshaker.utils.SharedUtils
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserConfig @Inject constructor(
    context: Context
) {

    companion object {
        private const val USER_CONFIG = "user_config"

        var user: User? = null
            set(value) {
                field = value
                userLiveData.postValue(user)
            }

        var userLiveData = MutableLiveData(user)

        fun logged() = user != null
    }

    private val sharedConfig = SharedUtils.sharedPrefs(context, USER_CONFIG)

    init {
        getUser()
    }

    fun saveUser(user: User?) {
        UserConfig.user = user
        sharedConfig.edit {
            putString("email", user?.email)
            putString("username", user?.userName)
            putString("first_name", user?.firstName)
            putString("last_name", user?.lastName)
            putLong("id", user?.id ?: 0)
            putString("profile_picture", user?.profilePicture)
        }
    }

    private fun getUser() {
        val user = User()
        user.email = sharedConfig.getString("email", "") ?: ""
        user.userName = sharedConfig.getString("username", "") ?: ""
        user.firstName = sharedConfig.getString("first_name", "") ?: ""
        user.lastName = sharedConfig.getString("last_name", "") ?: ""
        user.id = sharedConfig.getLong("id", 0)
        user.profilePicture = sharedConfig.getString("profile_picture", "") ?: ""
        UserConfig.user = user
    }

}