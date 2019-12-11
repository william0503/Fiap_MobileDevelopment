package br.com.will.pingpong

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setUpView()
    }

    private fun setObservers() {
        mainViewModel.homeScore.observe(this, Observer {homeScore ->
            tvHomeScore.text = homeScore.toString()
        })

        mainViewModel.awayScore.observe(this, Observer {awayScore ->
            tvAwayScore.text = awayScore.toString()
        })
    }

    private fun setUpView() {
        tvHomeName.text = intent.getStringExtra("HOME_NAME")
        tvAwayName.text = intent.getStringExtra("AWAY_NAME")
        setUpListener()
        setObservers()
    }

    private fun setUpListener() {
        btHomeScore.setOnClickListener{
            mainViewModel.goalHome()
        }
        btAwayScore.setOnClickListener {
            mainViewModel.goalAway()
        }

        ivVoltar.setOnClickListener{
            val nextScreen = Intent(this, PlayerActivity::class.java)
            startActivity(nextScreen)
        }
    }
}
