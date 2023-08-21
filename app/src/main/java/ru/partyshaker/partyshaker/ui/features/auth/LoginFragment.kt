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
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.AuthLoginBinding
import ru.partyshaker.partyshaker.ui.components.CustomOutlineTextInput
import ru.partyshaker.partyshaker.utils.InputAdapter
import ru.partyshaker.partyshaker.utils.SharedUtils
import ru.partyshaker.partyshaker.viewModels.LoginViewModel

@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {

    private val viewModel: LoginViewModel by activityViewModels()
    private lateinit var sharedUtils: SharedUtils
    private lateinit var inputAdapter: InputAdapter
    private lateinit var binding: AuthLoginBinding
    private lateinit var navRestorePass: Button
    private lateinit var navMyBar: Button
    private lateinit var email: CustomOutlineTextInput
    private lateinit var pass: CustomOutlineTextInput
    private lateinit var responseError: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = AuthLoginBinding.inflate(inflater, container, false)
        email = binding.email
        pass = binding.pass
        responseError = binding.responseError
        navMyBar = binding.navMyBar
        navRestorePass = binding.navRestorePassFirstStep

        navRestorePass.setOnClickListener(this)
        navMyBar.setOnClickListener(this)

        inputAdapter = InputAdapter(lifecycleScope)
        inputAdapter.setEmail(email)
        inputAdapter.setPassword(pass)

        sharedUtils = SharedUtils()
        sharedUtils.setSharedPrefs(requireContext(), "session")

        viewModel.loginError.observe(viewLifecycleOwner) {
            responseError.visibility = View.VISIBLE
            responseError.text = it.error
        }

        viewModel.activationRequiredError.observe(viewLifecycleOwner) {
            responseError.visibility = View.VISIBLE
            responseError.text = it.error
        }

        viewModel.activationRequired.observe(viewLifecycleOwner) {
            if (it == true) {
                viewModel.clearActivation()

                val emailArg = email.getEditText().text.toString()
                val passArg = pass.getEditText().text.toString()
                val action =
                    AuthFragmentDirections.actionAuthorizationFragmentToAuthAccActivationFragment(
                        emailArg, passArg
                    )
                findNavController().navigate(action)
            }
        }

        viewModel.login.observe(viewLifecycleOwner) {
            sharedUtils.setToken(it.refresh, it.access)
            findNavController().popBackStack(R.id.myBarFragment, false)
        }

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            navRestorePass.id -> {
                val emailArg = email.getEditText().text.toString()

                val action =
                    AuthFragmentDirections.actionAuthorizationFragmentToPassRecoveryFirstStepFragment(
                        emailArg
                    )
                findNavController().navigate(action)
            }

            navMyBar.id -> {
                val areFieldsValid =
                    inputAdapter.checkFieldsValid(requireContext(), email, pass, null)

                if (areFieldsValid) {
                    responseError.text = null
                    responseError.visibility = View.GONE

                    viewModel.checkLogin(
                        email.getEditText().text.toString(),
                        pass.getEditText().text.toString()
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }
}