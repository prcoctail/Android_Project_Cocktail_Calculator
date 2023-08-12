package ru.partyshaker.partyshaker.ui.features.cocktails.ui.filter

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

    private lateinit var cancelButton: Button
    private lateinit var applyButton: Button
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailsFilterBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cancelButton = binding.cocktailsFilterCancelButton
        applyButton = binding.cocktailsFilterApplyButton
        viewPager = binding.viewPager
        tabLayout = binding.tabLayout

        cancelButton.setOnClickListener(this)
        applyButton.setOnClickListener(this)

        adapter = CocktailsFilterAdapter(this)
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
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