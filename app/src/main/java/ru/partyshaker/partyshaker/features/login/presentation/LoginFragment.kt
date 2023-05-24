package ru.partyshaker.partyshaker.features.login.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.databinding.FragmentLoginBinding
import ru.partyshaker.partyshaker.features.login.domain.LoginRequest
import ru.partyshaker.partyshaker.features.login.presentation.viewmodels.LoginViewModel

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = requireNotNull(_binding)
    private val viewModel: LoginViewModel by viewModels()
    val loginRequest = LoginRequest("admin@admin.ru", "Admin123!")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.btnLogin.setOnClickListener{
            viewModel.getAuthToken(loginRequest)
        }
        return binding.root
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