package com.meu.imc_calculate

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.meu.imc_calculate.databinding.ActivityMainBinding
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
    }

    fun setListeners() {
        binding.btnCalculate.setOnClickListener {
            cal(
                binding.etPeso.text.toString(),
                binding.etAltura.text.toString()
            )
        }
    }

    fun cal(
        peso: String,
        altura: String
    ) {
        val peso = peso.toFloatOrNull()
        val altura = altura.toFloatOrNull()
        val imc = peso?.div((altura?.times(altura)!!))
        val form = DecimalFormat("#.##")

        if (peso != null && altura != null) {
            val classificacao = when {
                imc!! < 18.5 -> "Abaixo do peso"
                imc < 25 -> "Peso Normal"
                imc < 30 -> "Sobrepeso"
                imc < 35 -> "Obesidade Grau I"
                imc < 40 -> "Obesidade Grau II"
                else -> "Mórbida"
            }

            binding.titleIMC.text = "Seu IMC: ${form.format(imc)}"
            binding.classificacao.text = classificacao

            when(classificacao) {
                "Abaixo do peso" -> binding.titleIMC.setTextColor(Color.parseColor("#fdd835"))
                "Peso Normal" ->  binding.titleIMC.setTextColor(Color.parseColor("#43a047"))
                "Sobrepeso" -> binding.titleIMC.setTextColor(Color.parseColor("#fdd835"))
                "Obesidade Grau I" -> binding.titleIMC.setTextColor(Color.parseColor("#f57f17"))
                else -> binding.titleIMC.setTextColor(Color.parseColor("#f44336"))
            }
        }
    }
}
