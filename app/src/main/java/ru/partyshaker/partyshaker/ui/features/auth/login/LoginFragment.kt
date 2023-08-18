package ru.partyshaker.partyshaker.ui.features.auth.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.AuthLoginBinding
import ru.partyshaker.partyshaker.databinding.AuthRegisterBinding

@AndroidEntryPoint
class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: AuthLoginBinding
    private lateinit var applyButton: Button
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = AuthLoginBinding.inflate(inflater, container, false)
        applyButton = binding.navRestorePassFirstStep

        applyButton.setOnClickListener(this)

//        setUpTextWatchers()
//
//        binding.apply {
//            btnLogin.setOnClickListener {
//                if (isValid()) {
//                    val email = etEmail.getEditText().text?.trim()!!.toString()
//                    val password = etPassword.getEditText().text?.trim()!!.toString()
//                    viewModel.login(email, password)
//                }
//            }
//            viewModel.loginError.observe(viewLifecycleOwner) {
//                tvErrorText.apply {
//                    text = it.message
//                    visibility = View.VISIBLE
//                }
//            }
//            viewModel.loading.observe(viewLifecycleOwner) {
//
//            }
//        }
        return binding.root
    }

    private fun isValid(): Boolean {
        return isEmailValid() && isPasswordValid()
    }

    private fun isPasswordValid(): Boolean {
        val password = binding.pass.getEditText().text.toString().trim()
        return if (password.isEmpty()) {
            binding.pass.setErrorText(getString(R.string.empty_password_error_text))
            false
        } else if (password.length < 8) {
            binding.pass.setErrorText(getString(R.string.password_length_error_text))
            false
        } else {
            binding.pass.setErrorText(null)
            true
        }
    }

    private fun isEmailValid(): Boolean {
        val email = binding.email.getEditText().text.toString().trim()
        return if (email.isEmpty()) {
            binding.email.setErrorText(getString(R.string.empty_email_error_text))
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.email.setErrorText(getString(R.string.wrong_email_format_text))
            false
        } else {
            binding.email.setErrorText(null)
            true
        }
    }

    private fun setUpTextWatchers() {
        binding.email.getEditText().addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.email.setErrorText(null)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.pass.getEditText().addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.pass.setErrorText(null)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            applyButton.id -> {
                findNavController().navigate(R.id.action_authorizationFragment_to_passRecoveryFirstStepFragment)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }
}