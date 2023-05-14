package ru.partyshaker.partyshaker.cocktails

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.cocktails.data.data_classes.AdapterCocktailsList
import ru.partyshaker.partyshaker.databinding.FragmentCocktailsBinding
import javax.inject.Inject

@AndroidEntryPoint
class CocktailsFragment : Fragment() {

    private lateinit var binding : FragmentCocktailsBinding
    private val TAG = "CocktailsFragment"
    @Inject
    lateinit var repository: RepositoryCocktails
    private val viewModel by viewModels<ViewModelCocktails>()
    private val cocktailAdapter: AdapterCocktailsList by lazy { AdapterCocktailsList() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCocktailsBinding.inflate(inflater, container, false)
        println("THE LIST IS NOT NULL==================")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewCocktails.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewCocktails.adapter = cocktailAdapter

        viewModel.cocktailsList.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "onCreate: $it")
            if (it != null) {
                cocktailAdapter.setCocktailsList(it)
                println("THE LIST IS NOT NULL==================")
            }
            println("THE LIST NULL==================")
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Log.d(TAG, it.toString())
        })

        viewModel.getCocktailsAPI()
    }

}