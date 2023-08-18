package ru.partyshaker.partyshaker.ui.features.my_bar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.FragmentMyBarBinding

@AndroidEntryPoint
class MyBarFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentMyBarBinding
    private lateinit var authNavigate: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyBarBinding.inflate(inflater, container, false)
        authNavigate = binding.myBarAuthNavigateButton
        authNavigate.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            authNavigate.id -> {
                findNavController().navigate(R.id.auth_navigation)
            }
        }
    }
}