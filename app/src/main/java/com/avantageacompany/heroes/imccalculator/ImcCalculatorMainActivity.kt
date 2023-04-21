package com.avantageacompany.heroes.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.avantageacompany.heroes.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorMainActivity : AppCompatActivity() {

    private var isMaleSelected: Boolean = true
    private var isFemaleSelected: Boolean = false
    private var curtWeight: Int = 70
    private var curtAge: Int = 30
    private var curtHeight: Int = 120

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rangeHeight: RangeSlider
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton
    private lateinit var tvAge: TextView
    private lateinit var btnCalculate: Button

    companion object {
        const val IMC_KEY = "IMC_RESULT"
    }

    // fabCustomSize in xml to change the icons
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator_main)
        initComponents()
        initListeners()
        initUI()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.cardMale)
        viewFemale = findViewById(R.id.cardFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rangeHeight = findViewById(R.id.rangeHeight)
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        tvAge = findViewById(R.id.tvAge)
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rangeHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##")
            curtHeight = df.format(value).toInt()
            tvHeight.text = "$curtHeight cm"
        }
        btnPlusWeight.setOnClickListener {
            curtWeight += 1
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            curtWeight -= 1
            setWeight()
        }

        btnSubtractAge.setOnClickListener {
            curtAge -= 1
            setAge()
        }
        btnPlusAge.setOnClickListener {
            curtAge += 1
            setAge()
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent)
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = curtWeight / (curtHeight.toDouble() / 100 * curtHeight.toDouble() / 100)
        return df.format(imc).toDouble()

    }

    private fun setAge() {
        tvAge.text = curtAge.toString()
    }

    private fun setWeight() {
        tvWeight.text = curtWeight.toString()
    }

    private fun changeGender() {
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    private fun setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() {
        setGenderColor()
        setWeight()
        setAge()
    }
}