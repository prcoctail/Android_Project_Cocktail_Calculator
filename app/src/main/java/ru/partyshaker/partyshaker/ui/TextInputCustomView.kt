package ru.partyshaker.partyshaker.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.AppCompatEditText
import androidx.constraintlayout.widget.ConstraintLayout
import ru.partyshaker.partyshaker.R
import ru.partyshaker.partyshaker.databinding.TextInputCustomViewBinding

class TextInputCustomView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
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

    fun setSearchIconEnabled(state: Boolean) {
        if (state) {
            binding.textField.setStartIconDrawable(R.drawable.ic_search)
        } else {
            binding.textField.startIconDrawable = null
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
            textField.hint = hintText ?: "Label"

            val supportText = typedArray.getString(R.styleable.TextInputCustomView_supportText)
            textField.helperText = supportText ?: "Support text"

            val inputText = typedArray.getString(R.styleable.TextInputCustomView_inputText)
            etInput.setText(inputText ?: "")

            val searchIconEnabled =
                typedArray.getBoolean(R.styleable.TextInputCustomView_searchIconEnabled, false)
            setSearchIconEnabled(searchIconEnabled)

            val errorText = typedArray.getString(R.styleable.TextInputCustomView_errorText)
            textField.error = errorText
        }

        typedArray.recycle()
    }
}