package br.com.will.calculadoraflex.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.will.calculadoraflex.R
import br.com.will.calculadoraflex.User
import br.com.will.calculadoraflex.ui.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()
        btCreate.setOnClickListener {
            mAuth.createUserWithEmailAndPassword(
                inputEmail.text.toString(),
                inputPassword.text.toString()
            ).addOnCompleteListener {
                if (it.isSuccessful) {
                    saveInRealTimeDatabase()
                } else {
                    Toast.makeText(
                        this@SignUpActivity, it.exception?.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun saveInRealTimeDatabase() {
        val user = User(
            inputName.text.toString(), inputEmail.text.toString(),
            inputPhone.text.toString()
        )
        FirebaseDatabase.getInstance().getReference("Users")
            .child(FirebaseAuth.getInstance().currentUser!!.uid)
            .setValue(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(
                        this, "Usuário criado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    val returnIntent = Intent(this@SignUpActivity, LoginActivity::class.java)
                    returnIntent.putExtra("email", inputEmail.text.toString())
                    setResult(RESULT_OK, returnIntent)
                    finish()
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao criar o usuário", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
