package ru.partyshaker.partyshaker.ui.components

import android.content.Context
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.widget.ConstraintLayout
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.CustomOutlineTextInputBinding

class CustomOutlineTextInput @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    companion object {
        const val ERROR_STATE = "1"
        const val SEARCH_STATE = "2"
        const val NUMBER_INPUT_STATE = "3"
        const val EMAIL_INPUT_STATE = "4"
    }

    private val binding: CustomOutlineTextInputBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.custom_outline_text_input, this, true)
        binding = CustomOutlineTextInputBinding.bind(this)
        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    fun setErrorText(text: String?) {
        if (text == null) {
            binding.textField.error = null
        } else {
            binding.textField.error = text
        }
    }

    fun setHintText(text: String) {
        binding.textField.hint = text
    }

    fun setSupportText(text: String) {
        binding.textField.helperText = text
    }

    fun getEditText(): AppCompatEditText {
        return binding.etInput
    }

    private fun setInputType(inputType: String?) {
        when (inputType) {
            ERROR_STATE -> {
                setupPasswordState()
            }
            SEARCH_STATE -> {
                setSearchIconEnabled(true)
            }
            NUMBER_INPUT_STATE -> {
                setupNumberInputState()
            }
            EMAIL_INPUT_STATE -> {
                setupEmailInputState()
            }
            else -> {
                setSearchIconEnabled(false)
            }
        }
    }

    private fun setSearchIconEnabled(state: Boolean) {
        if (state) {
            binding.textField.setStartIconDrawable(R.drawable.ic_search)
        } else {
            binding.textField.startIconDrawable = null
        }
    }

    private fun setupEmailInputState() {
        setSearchIconEnabled(false)
        binding.etInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }

    private fun setupNumberInputState() {
        setSearchIconEnabled(false)
        binding.etInput.inputType = InputType.TYPE_CLASS_NUMBER
    }

    private fun setupPasswordState() {
        setSearchIconEnabled(false)

        binding.apply {
            etInput.transformationMethod = PasswordTransformationMethod.getInstance()
            textField.setEndIconDrawable(R.drawable.ic_invisible)

            textField.setEndIconOnClickListener {
                val currentTransformationMethod = etInput.transformationMethod
                val cursorPosition = etInput.selectionStart

                if (currentTransformationMethod == PasswordTransformationMethod.getInstance()) {
                    etInput.transformationMethod = HideReturnsTransformationMethod.getInstance()
                    textField.setEndIconDrawable(R.drawable.ic_visible)
                } else {
                    etInput.transformationMethod = PasswordTransformationMethod.getInstance()
                    textField.setEndIconDrawable(R.drawable.ic_invisible)
                }

                etInput.setSelection(cursorPosition)
            }
        }
    }

    private fun initializeAttributes(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.TextInputCustomView,
            defStyleAttr,
            defStyleRes
        )

        with(binding) {
            val hintText = typedArray.getString(R.styleable.TextInputCustomView_hintText)
            textField.hint = hintText ?: context.getString(R.string.default_hint_text)

            val supportText = typedArray.getString(R.styleable.TextInputCustomView_supportText)
            textField.helperText = supportText

            val inputText = typedArray.getString(R.styleable.TextInputCustomView_inputText)
            etInput.setText(inputText)

            val errorText = typedArray.getString(R.styleable.TextInputCustomView_errorText)
            textField.error = errorText

            val inputType = typedArray.getString(R.styleable.TextInputCustomView_inputType)
            setInputType(inputType)
        }

        typedArray.recycle()
    }
}