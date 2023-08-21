package ru.partyshaker.partyshaker.ui.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.databinding.AuthPassRestoreFirstStepBinding
import ru.partyshaker.partyshaker.ui.components.CustomOutlineTextInput
import ru.partyshaker.partyshaker.utils.InputAdapter
import ru.partyshaker.partyshaker.viewModels.PassRestoreFirstStepViewModel

@AndroidEntryPoint
class PassRestoreFirstStepFragment : Fragment(), View.OnClickListener {

    private val viewModel: PassRestoreFirstStepViewModel by activityViewModels()
    private val args: PassRestoreSecondStepFragmentArgs by navArgs()
    private lateinit var emailArg: String
    private lateinit var inputAdapter: InputAdapter
    private lateinit var binding: AuthPassRestoreFirstStepBinding
    private lateinit var email: CustomOutlineTextInput
    private lateinit var navPassRestoreSecondStep: Button
    private lateinit var responseError: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = AuthPassRestoreFirstStepBinding.inflate(inflater, container, false)
        email = binding.email
        navPassRestoreSecondStep = binding.navPassRestoreSecondStep
        responseError = binding.responseError
        emailArg = args.email

        navPassRestoreSecondStep.setOnClickListener(this)

        inputAdapter = InputAdapter(lifecycleScope)
        inputAdapter.setEmail(email)

        if (emailArg.isNotEmpty()) {
            email.getEditText().setText(emailArg)
        }

        viewModel.emailError.observe(viewLifecycleOwner) {
            responseError.visibility = View.VISIBLE
            responseError.text = it.error
        }

        viewModel.email.observe(viewLifecycleOwner) {
            if (it == true) {
                viewModel.clearEmail()

                val emailArg = email.getEditText().text.toString()
                val action =
                    PassRestoreFirstStepFragmentDirections
                        .actionPassRecoveryFirstStepFragmentToPassRecoverySecondStepFragment(
                            emailArg
                        )
                findNavController().navigate(action)
            }
        }

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            navPassRestoreSecondStep.id -> {
                val areFieldsValid =
                    inputAdapter.checkFieldsValid(
                        requireContext(),
                        email,
                        null,
                        null
                    )

                if (areFieldsValid) {
                    responseError.visibility = View.GONE
                    responseError.text = null
                }

                if (areFieldsValid && responseError.text.isNullOrEmpty()) {
                    viewModel.resetPassword(email.getEditText().text.toString())
                }
            }
        }
    }
}
