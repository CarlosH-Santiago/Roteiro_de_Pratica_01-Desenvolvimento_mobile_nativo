package br.com.chdevelopent.combustioncarapp.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import br.com.chdevelopent.combustioncarapp.R

class CalcularAutonomiaActivity : AppCompatActivity() {

    lateinit var preco: EditText
    lateinit var kmPercorrido: EditText
    lateinit var btnCalcular: Button
    lateinit var resultado: TextView
    lateinit var btnBackward: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calcular_autonomia)
        setupView()
        setupListeners()
    }


    fun setupView() {
         preco = findViewById(R.id.et_preco_combustivel)
         kmPercorrido = findViewById(R.id.et_km_percorrido)
         resultado = findViewById(R.id.tv_resultado)
         btnCalcular = findViewById(R.id.btn_calcular)
         btnBackward = findViewById(R.id.iv_backward)
    }

    fun setupListeners() {
        btnCalcular.setOnClickListener {
            calcular()
        }

        btnBackward.setOnClickListener {
            finish()
        }

    }
    fun calcular() {
            val preco = preco.text.toString().toFloat()
            val kmPercor = kmPercorrido.text.toString().toFloat()
            val result = preco / kmPercor

            resultado.text = result.toString()
    }




}