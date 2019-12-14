package br.com.will.calculadoraflex.ui.login

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.will.calculadoraflex.R
import br.com.will.calculadoraflex.extensions.isValid
import br.com.will.calculadoraflex.ui.form.FormActivity
import br.com.will.calculadoraflex.ui.signup.SignUpActivity
import br.com.will.calculadoraflex.watcher.FormValidationWatcher
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import javax.annotation.meta.When

class LoginActivity : AppCompatActivity() {

    val NEW_USER_REQUEST = 1
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        inputLoginEmail.editText?.addTextChangedListener(FormValidationWatcher(inputLoginEmail))
        inputLoginPassword.editText?.addTextChangedListener(FormValidationWatcher(inputLoginPassword))

        mAuth = FirebaseAuth.getInstance()
        mAuth.currentUser?.reload()

        if (mAuth.currentUser != null) {
            goToHome()
        }

        btSignup.setOnClickListener {
            val nextScreen = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivityForResult(nextScreen, NEW_USER_REQUEST)
        }

        btLogin.setOnClickListener {

            if(validate()) {
                mAuth.signInWithEmailAndPassword(
                    inputLoginEmail.editText?.text.toString(),
                    inputLoginPassword.editText?.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        goToHome()
                    } else {
                        Toast.makeText(
                            this@LoginActivity, it.exception?.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun validate() : Boolean{
        var isValid = true
        isValid = inputLoginEmail.isValid() && isValid
        isValid = inputLoginPassword.isValid() && isValid
        return isValid
    }

    private fun goToHome() {
        val intent = Intent(this, FormActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            NEW_USER_REQUEST -> {
                when (resultCode) {
                    Activity.RESULT_OK -> {
                        inputLoginEmail.editText?.setText(data?.getStringExtra("email"))
                    }
                }
            }
            else -> {

            }
        }
    }
}
