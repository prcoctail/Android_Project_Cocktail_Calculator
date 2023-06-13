package ru.partyshaker.partyshaker.ui.features.auth.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.PatternsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        setUpTextWatchers()

        binding.apply {
            btnLogin.setOnClickListener {
                if (isValid()) {
                    val email = etEmail.getEditText().text?.trim()!!.toString()
                    val password = etPassword.getEditText().text?.trim()!!.toString()
                    viewModel.login(email, password)
                }
            }
            viewModel.loginError.observe(viewLifecycleOwner) {
                tvErrorText.apply {
                    text = it.message
                    visibility = View.VISIBLE
                }
            }
            viewModel.loading.observe(viewLifecycleOwner) {

            }
        }
        return binding.root
    }

    private fun isValid(): Boolean {
        return isEmailValid() && isPasswordValid()
    }

    private fun isPasswordValid(): Boolean {
        val password = binding.etPassword.getEditText().text.toString().trim()
        return if (password.isEmpty()) {
            binding.etPassword.setErrorText(getString(R.string.empty_password_error_text))
            false
        } else if (password.length < 8) {
            binding.etPassword.setErrorText(getString(R.string.password_length_error_text))
            false
        } else {
            binding.etPassword.setErrorText(null)
            true
        }
    }

    private fun isEmailValid(): Boolean {
        val email = binding.etEmail.getEditText().text.toString().trim()
        return if (email.isEmpty()) {
            binding.etEmail.setErrorText(getString(R.string.empty_email_error_text))
            false
        } else if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.setErrorText(getString(R.string.wrong_email_format_text))
            false
        } else {
            binding.etEmail.setErrorText(null)
            true
        }
    }

    private fun setUpTextWatchers() {
        binding.etEmail.getEditText().addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etEmail.setErrorText(null)
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etPassword.getEditText().addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                binding.etPassword.setErrorText(null)
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}