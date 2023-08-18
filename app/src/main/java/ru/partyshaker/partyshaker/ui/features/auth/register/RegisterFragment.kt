package ru.partyshaker.partyshaker.ui.features.auth.register

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.AuthRegisterBinding
import ru.partyshaker.partyshaker.databinding.FragmentAuthBinding

class RegisterFragment : Fragment() {

    private lateinit var binding: AuthRegisterBinding
    private lateinit var policy: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthRegisterBinding.inflate(inflater, container, false)
        policy = binding.policy
//        setupPolicyLink()

        policy.setLinkTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.material_theme_sys_dark_primary
            )
        )

        return binding.root
    }

    private fun setupPolicyLink() {
        val text = getString(R.string.privacy_policy_label)
        val startIndex = text.indexOf(getString(R.string.privacy_policy))
        val endIndex = startIndex + getString(R.string.privacy_policy).length
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
        policy.text = spannableString
        policy.setLinkTextColor(
            ContextCompat.getColor(
                requireContext(),
                R.color.material_theme_sys_dark_primary
            )
        )
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }
}