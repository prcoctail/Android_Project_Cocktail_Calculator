package ru.partyshaker.partyshaker.ui.components

import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.textfield.TextInputLayout
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

    private var binding: CustomOutlineTextInputBinding
    private var textInput: TextInputLayout
    private var editText: AppCompatEditText

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.custom_outline_text_input, this, true)
        binding = CustomOutlineTextInputBinding.bind(this)
        textInput = binding.textInput
        editText = binding.editText

        initializeAttributes(attrs, defStyleAttr, defStyleRes)
    }

    fun getEditText(): AppCompatEditText {
        return editText
    }

    fun getErrorText(): CharSequence? {
        return textInput.error
    }

    fun getSuffix(): CharSequence? {
        return textInput.suffixText
    }

    fun setErrorText(text: String?, isEnabled: Boolean) {
        textInput.isErrorEnabled = isEnabled
        textInput.errorIconDrawable = null
        textInput.error = text
    }

    fun setLength(maxLength: Int) = if (maxLength > 0) {
        textInput.isCounterEnabled = true
        textInput.counterMaxLength = maxLength
        editText.filters = arrayOf(InputFilter.LengthFilter(maxLength))
    } else {
        textInput.isCounterEnabled = false
        textInput.counterMaxLength = 0
        editText.filters = null
    }

    fun setSuffix(text: String) {
        textInput.suffixText = text
    }

    fun setHintText(text: String) {
        textInput.hint = text
    }

    fun setSupportText(text: String) {
        textInput.helperText = text
    }

    private fun setInputType(inputType: String?) = when (inputType) {
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

    private fun setSearchIconEnabled(state: Boolean) = if (state) {
        textInput.setStartIconDrawable(R.drawable.ic_search)
    } else {
        textInput.startIconDrawable = null
    }

    private fun setupEmailInputState() {
        setSearchIconEnabled(false)
        editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
    }

    private fun setupNumberInputState() {
        setSearchIconEnabled(false)
        editText.inputType = InputType.TYPE_CLASS_NUMBER
    }

    private fun setupPasswordState() {
        setSearchIconEnabled(false)

        editText.transformationMethod = PasswordTransformationMethod.getInstance()
        textInput.setEndIconDrawable(R.drawable.ic_invisible)

        textInput.setEndIconOnClickListener {
            val currentTransformationMethod = editText.transformationMethod
            val cursorPosition = editText.selectionStart

            if (currentTransformationMethod == PasswordTransformationMethod.getInstance()) {
                editText.transformationMethod = HideReturnsTransformationMethod.getInstance()
                textInput.setEndIconDrawable(R.drawable.ic_visible)
            } else {
                editText.transformationMethod = PasswordTransformationMethod.getInstance()
                textInput.setEndIconDrawable(R.drawable.ic_invisible)
            }

            editText.setSelection(cursorPosition)
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

        val hintText = typedArray.getString(R.styleable.TextInputCustomView_hintText)
        textInput.hint = hintText ?: context.getString(R.string.default_hint_text)

        val supportText = typedArray.getString(R.styleable.TextInputCustomView_supportText)
        textInput.helperText = supportText

        val inputText = typedArray.getString(R.styleable.TextInputCustomView_inputText)
        editText.setText(inputText)

        val errorText = typedArray.getString(R.styleable.TextInputCustomView_errorText)
        textInput.error = errorText

        val inputType = typedArray.getString(R.styleable.TextInputCustomView_inputType)
        setInputType(inputType)

        typedArray.recycle()
    }
}