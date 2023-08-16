package ru.partyshaker.partyshaker.ui.features.ingredients.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.databinding.FragmentIngredientsBinding

@AndroidEntryPoint
class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIngredientsBinding.inflate(inflater, container, false)
        
        return binding.root
    }
}
