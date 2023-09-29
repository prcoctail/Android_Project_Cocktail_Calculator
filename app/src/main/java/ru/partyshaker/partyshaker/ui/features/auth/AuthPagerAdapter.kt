package ru.partyshaker.partyshaker.ui.features.auth

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.partyshaker.partyshaker.R

class AuthPagerAdapter(fragmentActivity: FragmentActivity, val context: Context) :
    FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        lateinit var fragment: Fragment

        when (position) {
            0 -> {
                fragment = LoginFragment()
            }

            1 -> {
                fragment = RegisterFragment()
            }
        }

        return fragment
    }

    fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null

        when (position) {
            0 -> {
                title =  context.resources.getString(R.string.title_login);
            }

            1 -> {
                title =  context.resources.getString(R.string.title_register);
            }
        }
        return title
    }
}
