package ru.partyshaker.partyshaker.ui.features.cocktails.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.databinding.FragmentCocktailsBinding

@AndroidEntryPoint
class CocktailsFragment : Fragment() {

    private lateinit var binding: FragmentCocktailsBinding
    private val TAG = "CocktailsFragment"

    private val viewModel by viewModels<ru.partyshaker.partyshaker.ui.features.cocktails.ui.ViewModelCocktails>()
    private val cocktailAdapter: ru.partyshaker.partyshaker.ui.features.cocktails.ui.AdapterCocktailsList by lazy { ru.partyshaker.partyshaker.ui.features.cocktails.ui.AdapterCocktailsList() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        println("hop")
        binding = FragmentCocktailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewCocktails.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewCocktails.adapter = cocktailAdapter

        viewModel.cocktailsList.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                cocktailAdapter.submitList(it)
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, it.toString())
        })

        viewModel.getCocktailsAPI()
    }

}