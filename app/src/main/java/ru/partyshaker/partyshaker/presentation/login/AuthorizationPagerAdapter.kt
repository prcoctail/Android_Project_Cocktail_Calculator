package ru.partyshaker.partyshaker.presentation.login

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class AuthorizationPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    enum class FragmentType {
        LOGIN, REGISTER
    }

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (FragmentType.values()[position]) {
            FragmentType.LOGIN -> LoginFragment()
            FragmentType.REGISTER -> RegisterFragment()
        }
    }
}
