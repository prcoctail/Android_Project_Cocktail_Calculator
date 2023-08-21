package ru.partyshaker.partyshaker.ui.features.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.AuthAccActivationBinding
import ru.partyshaker.partyshaker.utils.TimerUtil
import ru.partyshaker.partyshaker.viewModels.AccActivationViewModel

@AndroidEntryPoint
class AuthAccActivationFragment : Fragment(), View.OnClickListener {

    private val viewModel: AccActivationViewModel by activityViewModels()
    private val args: AuthAccActivationFragmentArgs by navArgs()
    private lateinit var emailArg: String
    private lateinit var passArg: String
    private lateinit var timerUtil: TimerUtil
    private lateinit var binding: AuthAccActivationBinding
    private lateinit var emailInfo: TextView
    private lateinit var responseError: TextView
    private lateinit var resendButton: Button
    private lateinit var applyButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = AuthAccActivationBinding.inflate(inflater, container, false)
        emailInfo = binding.emailInfo
        responseError = binding.responseError
        resendButton = binding.authAccResendButton
        applyButton = binding.navMyBar
        
        resendButton.setOnClickListener(this)
        applyButton.setOnClickListener(this)

        emailArg = args.email
        emailInfo.text = getString(R.string.acc_email_sent_label, emailArg)
        passArg = args.pass
        timerUtil = TimerUtil()

        viewModel.resendActivationError.observe(viewLifecycleOwner) {
            responseError.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.material_theme_sys_dark_error
                )
            )
            responseError.visibility = View.VISIBLE
            responseError.text = it.error
        }

        viewModel.endActivation.observe(viewLifecycleOwner) {
            responseError.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.material_theme_sys_dark_success
                )
            )
            responseError.visibility = View.VISIBLE
            responseError.text = "Аккаунт активирован, авторизуйтесь"

            resendButton.isEnabled = false
            resendButton.visibility = View.GONE
            binding.root.requestLayout()
        }

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            resendButton.id -> {
                resendRestoreLinkTimer()
                viewModel.checkLogin(emailArg, passArg)
            }

            applyButton.id -> {
                findNavController().popBackStack(R.id.authorizationFragment, false)
            }
        }
    }

    private fun resendRestoreLinkTimer() {
        val content = getString(R.string.acc_resend_button)
        val time =
            resources.getInteger(R.integer.auth_restore_resend_link_timer).toLong()

        timerUtil.setTimer(resendButton, content, time)
    }
}