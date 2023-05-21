package ru.partyshaker.partyshaker.features.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.partyshaker.partyshaker.presentation.login.RegisterFragment

class AuthorizationPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    enum class FragmentType {
        LOGIN, REGISTER
    }

    override fun getItemCount() = NUMBER_OF_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (FragmentType.values()[position]) {
            FragmentType.LOGIN -> LoginFragment()
            FragmentType.REGISTER -> RegisterFragment()
        }
    }

    companion object{
        private const val NUMBER_OF_PAGES = 2
    }
}
