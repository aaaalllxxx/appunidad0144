package com.example.appunidad0144

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appunidad0144.databinding.ActivityCalculadoraBinding

class CalculadoraActivity : AppCompatActivity() {

    private lateinit var b: ActivityCalculadoraBinding
    private val calculadora = Calculadora()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityCalculadoraBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(b.root)
        iniciarValores()
        eventosClic()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun iniciarValores() {
        b.txtUsuario.text = "Usuario: " + getString(R.string.nombre)
    }


    private fun obtenerNumeros(): Pair<Float, Float>? {
        val n1 = b.txtNum1.text.toString().trim()
        val n2 = b.txtNum2.text.toString().trim()

        if (n1.isEmpty() || n2.isEmpty()) {
            Toast.makeText(this, "Falto capturar información", Toast.LENGTH_SHORT).show()
            return null
        }

        val num1 = n1.toFloatOrNull()
        val num2 = n2.toFloatOrNull()
        if (num1 == null || num2 == null) {
            Toast.makeText(this, "Captura solo números válidos", Toast.LENGTH_SHORT).show()
            return null
        }

        return Pair(num1, num2)
    }

    private fun eventosClic() {

        b.btnSuma.setOnClickListener {
            val (num1, num2) = obtenerNumeros() ?: return@setOnClickListener
            calculadora.num1 = num1
            calculadora.num2 = num2
            b.txtResultado.text = "Resultado: " + String.format("%.2f", calculadora.suma())
        }

        b.btnResta.setOnClickListener {
            val (num1, num2) = obtenerNumeros() ?: return@setOnClickListener
            calculadora.num1 = num1
            calculadora.num2 = num2
            b.txtResultado.text = "Resultado: " + String.format("%.2f", calculadora.resta())
        }

        b.btnMulti.setOnClickListener {
            val (num1, num2) = obtenerNumeros() ?: return@setOnClickListener
            calculadora.num1 = num1
            calculadora.num2 = num2
            b.txtResultado.text = "Resultado: " + String.format("%.2f", calculadora.multiplicacion())
        }

        b.btnDiv.setOnClickListener {
            val (num1, num2) = obtenerNumeros() ?: return@setOnClickListener
            if (num2 == 0f) {
                Toast.makeText(this, "No se puede dividir entre 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            calculadora.num1 = num1
            calculadora.num2 = num2
            b.txtResultado.text = "Resultado: " + String.format("%.2f", calculadora.division())
        }


        b.btnLimpiar.setOnClickListener {
            b.txtNum1.setText("")
            b.txtNum2.setText("")
            b.txtResultado.text = getString(R.string.strExResultado)
            b.txtNum1.requestFocus()
        }

        b.btnRegresar.setOnClickListener {
            finish()
        }
    }
}