package ru.partyshaker.partyshaker.ui.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.AuthPassRestoreThirdStepBinding

@AndroidEntryPoint
class PassRestoreThirdStepFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: AuthPassRestoreThirdStepBinding
    private lateinit var applyButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthPassRestoreThirdStepBinding.inflate(inflater, container, false)
        applyButton = binding.navAuth

        applyButton.setOnClickListener(this)

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            applyButton.id -> {
                findNavController().popBackStack(R.id.authorizationFragment, false)
            }
        }
    }
}
