package br.com.will.calculadoraflex.ui.result

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.will.calculadoraflex.R
import br.com.will.calculadoraflex.extensions.format
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);


        setContentView(R.layout.activity_result)
        val valores = intent.extras

        if (valores == null) {
            Toast.makeText(this, "Não foi possível realizar a operação",
                Toast.LENGTH_SHORT).show()
        } else {
            calculate(valores)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun calculate(valores : Bundle) {
        val gasPrice = valores.getDouble("GAS_PRICE", 0.0)
        val ethanolPrice = valores.getDouble("ETHANOL_PRICE", 0.0)
        val gasAverage = valores.getDouble("GAS_AVERAGE", 0.0)
        val ethanolAverage = valores.getDouble("ETHANOL_AVERAGE", 0.0)
        val performanceOfMyCar = ethanolAverage / gasAverage
        val priceOfFuelIndice = ethanolPrice / gasPrice
        if (priceOfFuelIndice <= performanceOfMyCar) {
            tvSuggestion.text = getString(R.string.ethanol)
        } else {
            tvSuggestion.text = getString(R.string.gasoline)
        }
        tvEthanolAverageResult.text = (ethanolPrice / ethanolAverage).format(2)
        tvGasAverageResult.text = (gasPrice / gasAverage).format(2)
        tvFuelRatio.text =
            getString(R.string.label_fuel_ratio,performanceOfMyCar.format(2))

    }
}
