package ru.partyshaker.partyshaker.ui.features.auth

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.AuthRegisterBinding
import ru.partyshaker.partyshaker.ui.components.CustomOutlineTextInput
import ru.partyshaker.partyshaker.utils.InputAdapter
import ru.partyshaker.partyshaker.viewModels.RegisterViewModel

class RegisterFragment : Fragment(), View.OnClickListener {

    private val viewModel: RegisterViewModel by activityViewModels()
    private lateinit var inputAdapter: InputAdapter
    private lateinit var binding: AuthRegisterBinding
    private lateinit var navAuth: Button
    private lateinit var email: CustomOutlineTextInput
    private lateinit var pass: CustomOutlineTextInput
    private lateinit var rePass: CustomOutlineTextInput
    private lateinit var agreement: CheckBox
    private lateinit var agreementText: TextView
    private lateinit var responseError: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthRegisterBinding.inflate(inflater, container, false)
        navAuth = binding.navAuth
        email = binding.email
        pass = binding.pass
        rePass = binding.rePass
        agreement = binding.agreement
        agreementText = binding.agreementText
        responseError = binding.responseError

        agreementText.movementMethod = LinkMovementMethod.getInstance()

        navAuth.setOnClickListener(this)

        inputAdapter = InputAdapter(lifecycleScope)
        inputAdapter.setEmail(email)
        inputAdapter.setPassword(pass)
        inputAdapter.setRePassword(rePass, pass)

        viewModel.checkUserError.observe(viewLifecycleOwner) {
            responseError.visibility = View.VISIBLE
            responseError.text = it.email
        }

        viewModel.registerError.observe(viewLifecycleOwner) {
            responseError.visibility = View.VISIBLE
            responseError.text = getString(R.string.auth_register_format_error)
        }

        viewModel.checkUser.observe(viewLifecycleOwner) {
            val email = email.getEditText().text.toString()
            val password = pass.getEditText().text.toString()
            val rePassword = rePass.getEditText().text.toString()
            val agreement: String = if (agreement.isChecked) {
                "yes"
            } else {
                "no"
            }

            viewModel.register(email, password, rePassword, agreement)
        }

        viewModel.register.observe(viewLifecycleOwner) {
            val email = email.getEditText().text.toString()
            val password = pass.getEditText().text.toString()

            val action =
                AuthFragmentDirections.actionAuthorizationFragmentToAuthAccActivationFragment(
                    email,
                    password
                )
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.root.requestLayout()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            navAuth.id -> {
                val areFieldsValid = inputAdapter.checkFieldsValid(
                    requireContext(),
                    email,
                    pass,
                    rePass
                )

                if (agreement.isChecked) {
                    responseError.visibility = View.GONE
                    responseError.text = null
                } else {
                    responseError.visibility = View.VISIBLE
                    responseError.text =
                        resources.getString(R.string.auth_register_agreement_missing_error)
                }

                if (areFieldsValid && agreement.isChecked) {
                    viewModel.checkUser(email.getEditText().text.toString())
                }
            }
        }
    }
}