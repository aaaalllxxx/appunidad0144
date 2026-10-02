package com.example.appunidad0144

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appunidad0144.databinding.ActivityRectanguloBinding

class RectanguloActivity : AppCompatActivity() {

    private lateinit var b: ActivityRectanguloBinding
    private val rectangulo = Rectangulo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityRectanguloBinding.inflate(layoutInflater)
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
        b.txtNombre.text = getString(R.string.strNombreUsuario) + " " + getString(R.string.nombre)

        val adaptador = ArrayAdapter.createFromResource(
            this, R.array.operaciones, android.R.layout.simple_spinner_item
        )
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        b.spnRectangulo.adapter = adaptador
    }

    private fun eventosClic() {
        b.btnCalcular.setOnClickListener {
            val tBase = b.txtBase.text.toString().trim()
            val tAltura = b.txtPreAltura.text.toString().trim()

            // Todos los datos son requeridos
            if (tBase.isEmpty() || tAltura.isEmpty()) {
                Toast.makeText(this, "Falto capturar información", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Solo números decimales válidos
            val base = tBase.toFloatOrNull()
            val altura = tAltura.toFloatOrNull()
            if (base == null || altura == null) {
                Toast.makeText(this, "Captura solo números válidos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            rectangulo.base = base
            rectangulo.altura = altura

            val resultado = when (b.spnRectangulo.selectedItemPosition) {
                0 -> "Resultado: Área = " + String.format("%.2f", rectangulo.calculoArea())
                else -> "Resultado: Perímetro = " + String.format("%.2f", rectangulo.calculoPerimetro())
            }
            b.txtResultado.text = resultado
        }

        b.btnLimpiar.setOnClickListener {
            b.txtBase.setText("")
            b.txtPreAltura.setText("")
            b.spnRectangulo.setSelection(0)
            b.txtResultado.text = getString(R.string.strResultadoPreEx)
            b.txtBase.requestFocus()
        }

        b.btnSalir.setOnClickListener {
            finish()
        }
    }
}