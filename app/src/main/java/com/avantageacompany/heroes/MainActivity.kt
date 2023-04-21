package com.avantageacompany.heroes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.avantageacompany.heroes.imccalculator.ImcCalculatorMainActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnImcApp = findViewById<Button>(R.id.IMCApp)
        btnImcApp.setOnClickListener { navigateToImcApp() }
    }

    private fun navigateToImcApp() {
        val intent = Intent(this, ImcCalculatorMainActivity::class.java)
        startActivity(intent)
    }
}