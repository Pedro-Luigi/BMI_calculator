package com.meu.imc_calculate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    fun setListeners() {
        editTextAltura.doAfterTextChanged { }
        editTextPeso.doOnTextChanged { text, _, _, _ -> }
        button.setOnClickListener {
            cal(
                editTextPeso.text.toString(),
                editTextAltura.text.toString()
            )
        }
    }

    fun cal(
        peso: String,
        altura: String
    ) {
        val peso = peso.toFloatOrNull()
        val altura = altura.toFloatOrNull()
        val imc = peso?.div((altura?.times(altura!!)!!))
        val form = DecimalFormat("#.##")

        if (peso != null && altura != null) {
            val clas = when {
                imc!! < 18.5 -> "Abaixo do peso"
                imc < 25 -> "Peso Normal"
                imc < 30 -> "Sobrepeso"
                imc < 35 -> "Obesidade Grau I"
                imc < 40 -> "Obesidade Grau II"
                else -> "Mórbida"
            }

            titleIMC.text = "Seu IMC é de ${form.format(imc)}"
        }
    }
}
