package ru.partyshaker.partyshaker.ui.features.my_bar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.databinding.FragmentMyBarBinding

@AndroidEntryPoint
class MyBarFragment : Fragment() {

    private lateinit var binding: FragmentMyBarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyBarBinding.inflate(inflater, container, false)
        
        return binding.root
    }
}
