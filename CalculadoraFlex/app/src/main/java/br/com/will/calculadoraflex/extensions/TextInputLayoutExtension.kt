package br.com.will.calculadoraflex.extensions

import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.isValid() : Boolean{
    if(this.editText?.text.toString().isEmpty()){
        this.error = "Prencha o campo"
        return false
    }
    else{
        this.isErrorEnabled = false
        return true
    }
}