package br.com.will.calculadoraflex.ui.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.will.calculadoraflex.R
import br.com.will.calculadoraflex.extensions.isValid
import br.com.will.calculadoraflex.model.User
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
            if (validate()) {
                mAuth.createUserWithEmailAndPassword(
                    inputEmail.editText?.text.toString(),
                    inputPassword.editText?.text.toString()
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
    }

    private fun saveInRealTimeDatabase() {
        val user = User(
            inputName.editText?.text.toString(), inputEmail.editText?.text.toString(),
            inputPhone.editText?.text.toString()
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
                    val returnIntent = Intent()
                    returnIntent.putExtra("email", inputEmail.editText?.text.toString())
                    setResult(RESULT_OK, returnIntent)
                    finish()
                    finish()
                } else {
                    Toast.makeText(this, "Erro ao criar o usuário", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun validate(): Boolean {
        var isValid = true
        isValid = inputName.isValid() && isValid
        isValid = inputEmail.isValid() && isValid
        isValid = inputPassword.isValid() && isValid
        isValid = inputPhone.isValid() && isValid

        return isValid
    }

}
