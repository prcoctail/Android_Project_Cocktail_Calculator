package ru.partyshaker.partyshaker.ui

import android.content.Context
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.widget.ConstraintLayout
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.constants.EMAIL_INPUT_STATE
import ru.partyshaker.partyshaker.constants.ERROR_STATE
import ru.partyshaker.partyshaker.constants.NUMBER_INPUT_STATE
import ru.partyshaker.partyshaker.constants.SEARCH_STATE
import ru.partyshaker.partyshaker.databinding.TextInputCustomViewBinding

class TextInputCustomView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val binding: TextInputCustomViewBinding

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.text_input_custom_view, this, true)
        binding = TextInputCustomViewBinding.bind(this)
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

    fun setInputType(inputType: String?) {
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

    fun setSearchIconEnabled(state: Boolean) {
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