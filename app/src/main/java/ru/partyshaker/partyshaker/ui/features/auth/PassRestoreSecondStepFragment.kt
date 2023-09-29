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
import ru.partyshaker.partyshaker.databinding.AuthPassRestoreSecondStepBinding
import ru.partyshaker.partyshaker.utils.TimerUtil
import ru.partyshaker.partyshaker.viewModels.PassRestoreSecondStepViewModel

@AndroidEntryPoint
class PassRestoreSecondStepFragment : Fragment(), View.OnClickListener {

    private val viewModel: PassRestoreSecondStepViewModel by activityViewModels()
    private val args: PassRestoreSecondStepFragmentArgs by navArgs()
    private lateinit var emailArg: String
    private lateinit var timerUtil: TimerUtil
    private lateinit var binding: AuthPassRestoreSecondStepBinding
    private lateinit var emailInfo: TextView
    private lateinit var responseError: TextView
    private lateinit var resendActivationLink: Button
    private lateinit var navPassRestoreThirdStep: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        emailArg = args.email
        binding = AuthPassRestoreSecondStepBinding.inflate(inflater, container, false)
        emailInfo = binding.emailInfo
        responseError = binding.responseError
        resendActivationLink = binding.resendActivationLink
        navPassRestoreThirdStep = binding.navPassRestoreThirdStep

        timerUtil = TimerUtil()
        resendActivationLink.setOnClickListener(this)
        navPassRestoreThirdStep.setOnClickListener(this)
        emailInfo.text =
            getString(R.string.pass_recovery_second_step_email_sent_label, emailArg)

        viewModel.passRestoreError.observe(viewLifecycleOwner) {
            responseError.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.material_theme_sys_dark_error
                )
            )
            responseError.visibility = View.VISIBLE
            responseError.text = it.error
        }

        return binding.root
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            resendActivationLink.id -> {
                 viewModel.passRestore(emailArg)
                resendActivationLinkTimer()
            }

            navPassRestoreThirdStep.id -> {
                findNavController().popBackStack(R.id.authorizationFragment, false)
            }
        }
    }

    private fun resendActivationLinkTimer() {
        val content = getString(R.string.acc_resend_button)
        val time =
            resources.getInteger(R.integer.auth_restore_resend_link_timer).toLong()

        timerUtil.setTimer(resendActivationLink, content, time)
    }
}