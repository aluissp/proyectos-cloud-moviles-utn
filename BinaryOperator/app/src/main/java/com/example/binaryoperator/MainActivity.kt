package com.example.binaryoperator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private var radioAndIsChecked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Radio button
        val radioAnd = findViewById<RadioButton>(R.id.radio_and)
        val radioOr = findViewById<RadioButton>(R.id.radio_or)
        val input1 = findViewById<TextInputEditText>(R.id.first_input)
        val input2 = findViewById<TextInputEditText>(R.id.second_input)
        val labelResult = findViewById<TextView>(R.id.label_result)
        val btn = findViewById<Button>(R.id.button)

        radioAndIsChecked = radioAnd.isChecked

        radioAnd.setOnCheckedChangeListener { _: View, isChecked: Boolean ->
            radioAndIsChecked = isChecked
        }

        radioOr.setOnCheckedChangeListener { _: View, isChecked: Boolean ->
            radioAndIsChecked = !isChecked
        }

        btn.setOnClickListener { computeBinaryOperation(input1, input2, labelResult) }

    }

    private fun computeBinaryOperation(
        input1: TextInputEditText, input2: TextInputEditText, labelResult: TextView
    ) {
        Log.d("Info", "are radio and turn on? $radioAndIsChecked")
        val value1 = input1.text.toString()
        val value2 = input2.text.toString()
        var isInvalid = false
        var result = "Resultado: "

        if (value1 == "" || value2 == "") return showAlert("Por favor complete todos los campos!")

        if (value1.length != value2.length) return showAlert("Los valores deben tener la misma longitud!")

        isInvalid = value1.any { it != '0' && it != '1' }

        if (isInvalid) return showAlert("Los valores ingresados son inválidos!")

        isInvalid = value2.any { it != '0' && it != '1' }

        if (isInvalid) return showAlert("Los valores ingresados son inválidos!")

        val numberZipped = value1 zip value2

        numberZipped.forEach { (a, b) ->
            var numberComputed = a.digitToInt() + b.digitToInt()

            if (radioAndIsChecked) {
                numberComputed = if (numberComputed == 2) 1 else 0
            } else if (numberComputed == 2) numberComputed = 1

            result += numberComputed.toString()
        }

        labelResult.text = result
    }

    private fun showAlert(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }
}