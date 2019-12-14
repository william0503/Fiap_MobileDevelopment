package br.com.will.calculadoraflex.watcher

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import java.lang.ref.WeakReference

class FormValidationWatcher(input: TextInputLayout) :
    TextWatcher {
    private val textInputLayout: WeakReference<TextInputLayout> =
        WeakReference(input)

    init {
        checkField(textInputLayout.get()!!.editText!!.text)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(editable: Editable) {
        checkField(editable)
    }

    private fun checkField(editable: Editable) {
        val input = textInputLayout.get() ?: return
        if (editable.length > 0) {
            input.isErrorEnabled = false
        }
    }
}