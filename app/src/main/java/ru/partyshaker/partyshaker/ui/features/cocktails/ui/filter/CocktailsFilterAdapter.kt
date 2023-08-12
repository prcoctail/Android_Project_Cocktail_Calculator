package ru.partyshaker.partyshaker.ui.features.cocktails.ui.filter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class CocktailsFilterAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        lateinit var fragment: Fragment

        when (position) {
            0 -> {
                fragment = CocktailsFilterCategoriesFragment()
            }

            1 -> {
                fragment = CocktailsFilterIngredientsFragment()
            }
        }

        return fragment
    }

    fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null

        when (position) {
            0 -> {
                title = "Категории"
            }

            1 -> {
                title = "Ингредиенты"
            }
        }
        return title
    }
}