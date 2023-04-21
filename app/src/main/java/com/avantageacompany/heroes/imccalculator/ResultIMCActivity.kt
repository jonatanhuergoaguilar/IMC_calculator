package com.avantageacompany.heroes.imccalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.avantageacompany.heroes.R
import com.avantageacompany.heroes.imccalculator.ImcCalculatorMainActivity.Companion.IMC_KEY

class ResultIMCActivity : AppCompatActivity() {

    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView
    private lateinit var btRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        val result = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initComponents()
        initUI(result)
        initListeners()
    }

    private fun initListeners() {
        btRecalculate.setOnClickListener { onBackPressedDispatcher.onBackPressed() }
    }

    private fun initUI(result: Double) {
        tvIMC.text = result.toString()
        when (result) {
            in 0.00..18.50 -> {
                tvResult.text = getString(R.string.underweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.underweight))
                tvDescription.text = getString(R.string.underweight_description)
            }

            in 18.51..24.99 -> {
                tvResult.text = getString(R.string.normal_weight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal_weight))
                tvDescription.text = getString(R.string.normal_weight_description)
            }

            in 25.00..29.99 -> {
                tvResult.text = getString(R.string.overweight)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.overweight))
                tvDescription.text = getString(R.string.overweight_description)
            }

            in 30.00..99.00 -> {
                tvResult.text = getString(R.string.obesity)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obesity))
                tvDescription.text = getString(R.string.obesity_description)
            }

            else -> {
                tvIMC.text = getString(R.string.error)
                tvResult.text = getString(R.string.error)
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.error))
                tvDescription.text = getString(R.string.error)
            }
        }
    }

    private fun initComponents() {
        tvIMC = findViewById(R.id.tvIMC)
        tvResult = findViewById(R.id.tvResult)
        tvDescription = findViewById(R.id.tvDescription)
        btRecalculate = findViewById(R.id.btnRecalculate)
    }
}