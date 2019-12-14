package br.com.will.pingpong.extensions

import android.widget.EditText

fun EditText.value() = this.text.toString()