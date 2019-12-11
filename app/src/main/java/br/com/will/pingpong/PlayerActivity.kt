package br.com.will.pingpong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.will.pingpong.extensions.value
import kotlinx.android.synthetic.main.activity_player.*

class PlayerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        btStart.setOnClickListener {
            startGame()
        }
    }

    private fun startGame() {
        val nextScreen = Intent(this, MainActivity::class.java)

        nextScreen.putExtra("HOME_NAME", etHomeName.value())
        nextScreen.putExtra("AWAY_NAME", etAwayName.value())

        startActivity(nextScreen)
        finish()
    }
}
