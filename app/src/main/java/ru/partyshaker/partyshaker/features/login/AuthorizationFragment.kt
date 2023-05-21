package ru.partyshaker.partyshaker.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.FragmentAuthorizationBinding
import ru.partyshaker.partyshaker.features.login.AuthorizationPagerAdapter

class AuthorizationFragment : Fragment() {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)

        binding.vpAuthorization.apply {
            adapter = AuthorizationPagerAdapter(requireActivity())
        }

        TabLayoutMediator(binding.tabLayout, binding.vpAuthorization) { tab, index ->
            tab.text = when (index) {
                LOGIN -> {
                    getString(R.string.authorize)
                }
                SIGN_UP -> {
                    getString(R.string.register)
                }
                else -> {
                    getString(R.string.fragment_not_found_text)
                }
            }
        }.attach()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val LOGIN = 0
        private const val SIGN_UP = 1
    }
}