package br.com.will.calculadoraflex.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.will.calculadoraflex.R
import br.com.will.calculadoraflex.ui.signup.SignUpActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btSignup.setOnClickListener {
            val nextScreen = Intent(this@LoginActivity, SignUpActivity::class.java)
            startActivity(nextScreen)
        }
    }
}
