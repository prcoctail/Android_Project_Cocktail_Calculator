package ru.partyshaker.partyshaker.utils

import android.content.Context
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.ui.components.CustomOutlineTextInput

class InputAdapter(private val scope: LifecycleCoroutineScope) {
    fun setEmail(email: CustomOutlineTextInput) {
        with(email) {
            val minLength = resources.getInteger(R.integer.auth_email_min_length)
            val maxLength = resources.getInteger(R.integer.auth_email_max_length)
            val checkDelay = resources.getInteger(R.integer.auth_input_filed_check_delay).toLong()
//            val suffix = resources.getString(R.string.auth_email_suffix)
            val emailWrongLength = resources.getString(R.string.field_email_wrong_length)
            val emailWrongFormat = resources.getString(R.string.field_email_wrong_format)
            var job: Job = Job()

            setLength(maxLength)
//            setSuffix(suffix)

            getEditText().doOnTextChanged { text, _, _, _ ->
                job.cancel()
                job = scope.launch {
                    delay(checkDelay)

                    // region [REG EXP VALUES]
                    val textContent = "$text"

//                    val textWithSuffixContent = "$text$suffix"
                    val regExWrongFormat = "^[A-Za-z0-9\\-._]+@[A-Za-z0-9\\-_]+\\.[A-Za-z]+$"
                        .toRegex(RegexOption.IGNORE_CASE)
                    val matchResult = textContent.matches(regExWrongFormat)

                    val regExWrongSymbols = "[^A-Za-z0-9.\\-_@]+"
                        .toRegex(RegexOption.IGNORE_CASE)
                    val matchWrongSymbols = getWrongSymbols(regExWrongSymbols, textContent)
                    // endregion [REG EXP VALUES]

                    // region [VALIDATION]
                    when {
                        matchWrongSymbols.isNotEmpty() -> {
                            setErrorText(
                                context.getString(
                                    R.string.auth_pass_wrong_symbols,
                                    matchWrongSymbols
                                ), true
                            )
                        }

                        textContent.isNotEmpty() && !matchResult
                                && textContent.length >= minLength -> {
                            setErrorText(emailWrongFormat, true)
                        }

                        textContent.isNotEmpty() && textContent.length < minLength -> {
                            setErrorText(emailWrongLength, true)
                        }

                        else -> {
                            setErrorText(null, false)
                        }
                    }
                    // endregion [VALIDATION]
                }
            }
        }
    }

    fun setPassword(pass: CustomOutlineTextInput) {
        with(pass) {
            val minLength = resources.getInteger(R.integer.auth_password_min_length)
            val maxLength = resources.getInteger(R.integer.auth_password_max_length)
            val checkDelay = resources.getInteger(R.integer.auth_input_filed_check_delay).toLong()
            val passWrongLength = resources.getString(R.string.field_pass_wrong_length)
            val passMissingLetters = resources.getString(R.string.field_pass_missing_letters)
            val passMissingNumbers = resources.getString(R.string.field_pass_missing_numbers)
            val passMissingSigns = resources.getString(R.string.field_pass_missing_signs)
            val passMissingUpperCase = resources.getString(R.string.field_pass_missing_upper_case)
            val passMissingLowerCase = resources.getString(R.string.field_pass_missing_lower_case)
            var job: Job = Job()

            setLength(maxLength)

            getEditText().doOnTextChanged { text, _, _, _ ->
                job.cancel()
                job = scope.launch {
                    delay(checkDelay)

                    // region [REG EXP VALUES]
                    val textContent = "$text"

                    val regExWrongSymbols =
                        "[^A-Za-z\\d!?@#$%^&*\"\'|(){}\\]\\[<>:/;.,_~+=\\-]+".toRegex()
                    val matchWrongSymbols = getWrongSymbols(regExWrongSymbols, textContent)

                    val regExMissingLetters = "[A-Za-z]".toRegex()
                    val matchMissingLetters = getMissingSymbols(regExMissingLetters, textContent)

                    val regExMissingNumbers = "[0-9]".toRegex()
                    val matchMissingNumbers = getMissingSymbols(regExMissingNumbers, textContent)

                    val regExMissingSigns =
                        "[!?@#$%^&*\"\'|(){}\\]\\[<>:/;.,_~+=\\-]+".toRegex()
                    val matchMissingSigns = getMissingSymbols(regExMissingSigns, textContent)

                    val regExMissingUpperCase = "[A-Z]".toRegex()
                    val matchMissingUpperCase =
                        getMissingSymbols(regExMissingUpperCase, textContent)

                    val regExMissingLowerCase = "[a-z]".toRegex()
                    val matchMissingLowerCase =
                        getMissingSymbols(regExMissingLowerCase, textContent)
                    // endregion [REG EXP VALUES]

                    // region [VALIDATION]
                    when {
                        matchWrongSymbols.isNotEmpty() -> {
                            setErrorText(
                                context.getString(
                                    R.string.auth_pass_wrong_symbols,
                                    matchWrongSymbols
                                ), true
                            )
                        }

                        textContent.isNotEmpty() && matchMissingLetters.isEmpty() -> {
                            setErrorText(passMissingLetters, true)
                        }

                        textContent.isNotEmpty() && matchMissingUpperCase.isEmpty() -> {
                            setErrorText(passMissingUpperCase, true)
                        }

                        textContent.isNotEmpty() && matchMissingLowerCase.isEmpty() -> {
                            setErrorText(passMissingLowerCase, true)
                        }

                        textContent.isNotEmpty() && matchMissingNumbers.isEmpty() -> {
                            setErrorText(passMissingNumbers, true)
                        }

                        textContent.isNotEmpty() && matchMissingSigns.isEmpty() -> {
                            setErrorText(passMissingSigns, true)
                        }

                        textContent.isNotEmpty() &&
                                textContent.length !in minLength..maxLength -> {
                            setErrorText(passWrongLength, true)
                        }

                        else -> {
                            setErrorText(null, false)
                        }
                    }
                    // endregion [VALIDATION]
                }
            }
        }
    }

    fun setRePassword(rePass: CustomOutlineTextInput, pass: CustomOutlineTextInput) {
        with(rePass) {
            val minLength = resources.getInteger(R.integer.auth_password_min_length)
            val maxLength = resources.getInteger(R.integer.auth_password_max_length)
            val checkDelay = resources.getInteger(R.integer.auth_input_filed_check_delay).toLong()
            val rePassWrongLength = resources.getString(R.string.field_pass_wrong_length)
            val rePassWrongMatch = context.getString(R.string.auth_register_re_password_wrong_match)
            var job: Job = Job()

            setLength(maxLength)

            // region [VALIDATION]
            getEditText().doOnTextChanged { text, _, _, _ ->
                job.cancel()
                job = scope.launch {
                    delay(checkDelay)
                    val textContent = "$text"
                    val passContent = pass.getEditText().text.toString()

                    when {
                        textContent.isNotEmpty() && passContent != textContent -> {
                            setErrorText(rePassWrongMatch, true)
                        }

                        textContent.isNotEmpty() && textContent.length !in minLength..maxLength -> {
                            setErrorText(rePassWrongLength, true)
                        }

                        textContent.isEmpty() && passContent.isEmpty() -> {
                            pass.setErrorText(null, false)
                        }

                        else -> {
                            setErrorText(null, false)
                        }
                    }
                }
            }

            with(pass) {
                getEditText().doOnTextChanged { text, _, _, _ ->
                    job.cancel()
                    job = scope.launch {
                        delay(checkDelay)
                        val textContent = "$text"
                        val passToMatchContent = rePass.getEditText().text.toString()

                        if (passToMatchContent.isNotEmpty() && passToMatchContent != textContent
                        ) {
                            rePass.setErrorText(rePassWrongMatch, true)
                        } else {
                            rePass.setErrorText(null, false)
                        }
                    }
                }
            }
            // endregion [VALIDATION]
        }
    }

    fun checkFieldsValid(
        context: Context,
        email: CustomOutlineTextInput?,
        pass: CustomOutlineTextInput?,
        rePass: CustomOutlineTextInput?
    ): Boolean {
        val emailContent = email?.getEditText()?.text?.isEmpty() ?: false
        // val emailContentWithSuffix = !emailContent.plus(email?.getSuffix())?.isEmpty()
        val passContent = pass?.getEditText()?.text?.isEmpty() ?: false
        val rePassContent = rePass?.getEditText()?.text?.isEmpty() ?: false

        var emailError = email?.getErrorText().isNullOrEmpty()
        var passError = pass?.getErrorText().isNullOrEmpty()
        var rePassError = rePass?.getErrorText().isNullOrEmpty()

        if (email != null) {
            when {
                emailContent && emailError -> {
                    email.setErrorText(
                        context.getString(R.string.auth_register_email_missing_error),
                        true
                    )
                    emailError = false
                }

                !emailError -> {
                    emailError = false
                }
            }
        }

        if (pass != null) {
            when {
                passContent && passError -> {
                    pass.setErrorText(
                        context.getString(R.string.auth_register_pass_missing_error),
                        true
                    )
                    passError = false
                }

                !passError -> {
                    passError = false
                }
            }
        }

        if (rePass != null) {
            when {
                rePassContent && rePassError -> {
                    rePass.setErrorText(
                        context.getString(R.string.auth_register_re_pass_missing_error), true
                    )
                    rePassError = false
                }

                !rePassError -> {
                    rePassError = false
                }
            }
        }

        return emailError && passError && rePassError
    }

    private fun getWrongSymbols(regEx: Regex, content: CharSequence?): String =
        regEx.findAll("$content")
            .map { it.value }
            .joinToString("")
            .toCharArray()
            .toSet()
            .joinToString(", ", transform = { "«$it»" })

    private fun getMissingSymbols(regEx: Regex, content: CharSequence?): String =
        regEx.findAll("$content")
            .map { it.value }
            .joinToString("")
}