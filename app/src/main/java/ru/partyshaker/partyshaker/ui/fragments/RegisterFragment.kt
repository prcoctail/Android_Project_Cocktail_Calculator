package ru.partyshaker.partyshaker.ui.fragments

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        setupPolicyLink()

        return binding.root
    }

    private fun setupPolicyLink() {
        val text = "Мне исполнилось 18 лет и я согласен с Политикой конфидециальности"
        val startIndex = text.indexOf("Политикой конфидециальности")
        val endIndex = startIndex + "Политикой конфидециальности".length
        val spannableString = SpannableString(text)

        val clickableSpan = object : ClickableSpan() {

            override fun onClick(view: View) {
                // Handle the click event
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.color = ds.linkColor
                ds.isUnderlineText = false
            }
        }

        spannableString.setSpan(
            clickableSpan,
            startIndex,
            endIndex,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvPolicyLabel.text = spannableString
        binding.tvPolicyLabel.setLinkTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.material_theme_sys_dark_primary
            )
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}