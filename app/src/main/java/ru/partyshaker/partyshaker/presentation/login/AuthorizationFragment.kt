package ru.partyshaker.partyshaker.presentation.login

import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import ru.partyshaker.partyshaker.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : Fragment() {

    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)

        binding.vpAuthorization.apply {
            adapter = AuthorizationPagerAdapter(requireActivity())
        }

        TabLayoutMediator(binding.tabLayout, binding.vpAuthorization) { tab, index ->
            tab.text = when (index) {
                0 -> {
                    "Вход"
                }
                1 -> {
                    "Регистрация"
                }
                else -> {
                    throw Resources.NotFoundException("Position Not Found")
                }
            }
        }.attach()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}