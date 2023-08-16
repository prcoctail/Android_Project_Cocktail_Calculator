package ru.partyshaker.partyshaker.ui.features.cocktails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.FragmentCocktailsBinding
import ru.partyshaker.partyshaker.ui.features.cocktails.data.data_classes.Cocktail


@AndroidEntryPoint
class CocktailsFragment : Fragment(), View.OnClickListener, CocktailsAdapter.Listener {

    private val viewModel by viewModels<CocktailsViewModel>()
    private val cocktailAdapter = CocktailsAdapter(this)

    private lateinit var binding: FragmentCocktailsBinding
    private lateinit var cocktailsRecycler: RecyclerView
    private lateinit var bannerCloseButton: ImageButton
    private lateinit var cocktailsFilterButton: ImageButton
    private lateinit var cocktailsSearchButton: ImageButton
    private lateinit var banner: CardView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailsBinding.inflate(inflater, container, false)
        cocktailsRecycler = binding.cocktailsRecycler
        bannerCloseButton = binding.bannerCloseButton
        cocktailsFilterButton = binding.cocktailsFilterButton
        cocktailsSearchButton = binding.cocktailsSearchButton
        banner = binding.banner

        viewModel.cocktails.observe(viewLifecycleOwner) { cocktails ->
            cocktailAdapter.submitList(cocktails)

            with(cocktailsRecycler) {
                val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.divider_cocktails_fragment_list,
                    null
                )
                    ?.let {
                        itemDecoration.setDrawable(it)
                        addItemDecoration(itemDecoration)
                    }

                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = cocktailAdapter
            }
        }

        bannerCloseButton.setOnClickListener(this)
        cocktailsFilterButton.setOnClickListener(this)
        cocktailsSearchButton.setOnClickListener(this)

        return binding.root
    }

    override fun navigateToCocktailDetails(cocktail: Cocktail) {
        findNavController().navigate(R.id.action_cocktailsFragment_to_cocktailCardFragment)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            bannerCloseButton.id -> {
                banner.visibility = View.GONE
            }

            cocktailsFilterButton.id -> {
                findNavController().navigate(R.id.action_cocktailsFragment_to_cocktailsFilterFragment)
            }
        }
    }
}