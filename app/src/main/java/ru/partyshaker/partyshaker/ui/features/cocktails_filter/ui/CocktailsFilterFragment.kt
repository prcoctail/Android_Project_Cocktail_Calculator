package ru.partyshaker.partyshaker.ui.features.cocktails_filter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.databinding.FragmentCocktailsFilterBinding

@AndroidEntryPoint
class CocktailsFilterFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentCocktailsFilterBinding
    private lateinit var adapter: CocktailsFilterAdapter

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var cancelButton: Button
    private lateinit var applyButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailsFilterBinding.inflate(inflater, container, false)
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager
        cancelButton = binding.cocktailsFilterCancelButton
        applyButton = binding.cocktailsFilterApplyButton

        adapter = CocktailsFilterAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()

        cancelButton.setOnClickListener(this)
        applyButton.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            cancelButton.id -> {

            }

            applyButton.id -> {

            }
        }
    }
}