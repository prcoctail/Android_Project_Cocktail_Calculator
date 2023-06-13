package ru.partyshaker.partyshaker.features.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.FragmentMainBinding

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonNavigateLogin.setOnClickListener {
            launchLoginFragment()
        }
        binding.buttonNavigateToCocktailList.setOnClickListener{
            findNavController().navigate(R.id.action_mainFragment_to_cocktailsFragment)
        }
    }

    private fun launchLoginFragment() {
        findNavController().navigate(R.id.action_mainFragment_to_authorizationFragment)
    }
}