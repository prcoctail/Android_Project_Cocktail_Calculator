package ru.partyshaker.partyshaker.ui.features.cocktail_card.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.databinding.FragmentCocktailCardBinding

@AndroidEntryPoint
class CocktailCardFragment : Fragment() {

    private lateinit var binding: FragmentCocktailCardBinding
    private val TAG = "CocktailCardFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailCardBinding.inflate(inflater, container, false)
        return binding.root
    }
}