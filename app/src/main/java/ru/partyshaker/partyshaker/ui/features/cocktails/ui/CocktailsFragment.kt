package ru.partyshaker.partyshaker.ui.features.cocktails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.FragmentCocktailsBinding
import ru.partyshaker.partyshaker.ui.features.cocktails.CocktailsViewModel
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail

@AndroidEntryPoint
class CocktailsFragment : Fragment(), View.OnClickListener, CocktailsAdapter.Listener {

    private lateinit var binding: FragmentCocktailsBinding
    private val TAG = "CocktailsFragment"

    private val viewModel by viewModels<ViewModelCocktails>()
    private val cocktailAdapter: AdapterCocktailsList by lazy { AdapterCocktailsList() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailsBinding.inflate(inflater, container, false)

        binding.cocktailsRecycler.apply {
            adapter = cocktailAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)

            val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            ResourcesCompat.getDrawable(resources, R.drawable.divider_cocktails_fragment_list, null)
                ?.let {
                    itemDecoration.setDrawable(it)
                    addItemDecoration(itemDecoration)
                }
        }

        binding.cocktailBannerCloseButton.setOnClickListener(this)
        binding.cocktailsFilterButton.setOnClickListener(this)
        binding.cocktailsSearchButton.setOnClickListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllCocktails().observe(viewLifecycleOwner) {
            cocktailAdapter.submitList(it?.results)
        }
    }

    override fun onClick(cocktail: Cocktail) {
        Toast.makeText(requireContext(), "READY", Toast.LENGTH_SHORT).show()
    }

    override fun cocktailBannerCloseClick(view: CardView) {
        binding.cocktailsBanner
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.cocktailBannerCloseButton.id -> {
                binding.cocktailsBanner.visibility = View.GONE
            }

        binding.cocktailsFilter.setOnClickListener{
            findNavController().navigate(R.id.action_cocktailsFragment_to_cocktailsFilterFragment)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })

            binding.cocktailsFilterButton.id -> {

            }

            binding.cocktailsSearchButton.id -> {

            }
        }
    }
}