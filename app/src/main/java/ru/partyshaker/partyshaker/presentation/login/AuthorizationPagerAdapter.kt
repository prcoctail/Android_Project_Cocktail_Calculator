package ru.partyshaker.partyshaker.presentation.login

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.partyshaker.partyshaker.constants.fragmentNotFoundExceptionText

class AuthorizationPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                LoginFragment()
            }
            1 -> {
                RegisterFragment()
            }
            else -> {
                throw Resources.NotFoundException(fragmentNotFoundExceptionText)
            }
        }
    }
}
