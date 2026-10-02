package com.example.appunidad0144

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appunidad0144.databinding.ActivityCotizacionBinding
import com.example.appunidad0144.databinding.ActivityMainBinding

class CotizacionActivity : AppCompatActivity() {
    private lateinit var txtCliente: TextView
    private lateinit var txtFolio: TextView
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPrecio: EditText
    private lateinit var txtPorPagI: EditText
    private lateinit var rdb12: RadioButton
    private lateinit var rdb24: RadioButton
    private lateinit var rdb36: RadioButton
    private lateinit var rdb48: RadioButton
    private lateinit var txtPagoInc: TextView
    private lateinit var txtTotalFin: TextView
    private lateinit var txtPlazos: TextView
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnSalir: Button

    private lateinit var b: ActivityCotizacionBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        b= ActivityCotizacionBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(b.root)
        eventosClic()
        iniciarValores()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun iniciarValores(){
        val strCliente = intent.getStringExtra("cliente") ?: ""
        val cotizacion = Cotizacion()
        val numFolio= cotizacion.generarNumCotizacion()


        b.txtCliente.text= strCliente
        b.txtFolio.text= numFolio.toString()


    }
    fun eventosClic(){
        b.btnCalcular.setOnClickListener {

            if (b.txtDescripcion.text.toString().contentEquals("")
                || b.txtPrecio.text.toString().contentEquals("")
                || b.txtPorcentaje.text.toString().contentEquals("")){
                Toast.makeText(this, "Falto Capturar Información", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val cotizacion= Cotizacion();
            cotizacion.descripcion= b.txtDescripcion.text.toString()
            cotizacion.precio= b.txtPrecio.text.toString().toFloat()
            cotizacion.porPagoInc= b.txtPorcentaje.text.toString().toFloat()

            //plazos
            if (b.rdb12.isChecked) cotizacion.plazos= 12
            if (b.rdb24.isChecked) cotizacion.plazos= 24
            if (b.rdb36.isChecked) cotizacion.plazos= 36
            if (b.rdb48.isChecked) cotizacion.plazos= 48


            b.txtPagoInicial.text = "Pago Inicial: $" + cotizacion.calcularPagoInicial()
            b.txtTotalFin.text = "Total a pagar: $" + cotizacion.calcularTotalFin()
            b.txtPagoMensual.text= "Pago Mensual: $" + cotizacion.calcularMensual()

        }
        b.btnLimpiar.setOnClickListener {
            val cotizacion = Cotizacion()
            b.txtFolio.text= ""
            b.txtDescripcion.setText("")
            b.txtPrecio.setText("")
            b.txtPorcentaje.setText("")


            b.rdb12.isChecked= true
            b.txtPagoInicial.text = "Pago Inicial= "
            b.txtTotalFin.text = "Total a Financiar: "
            b.txtPagoMensual.text = "Plazos"
            //mandar el foco de atención a la descripción

            b.txtDescripcion.requestFocus()
        }

        b.btnCerrar.setOnClickListener {
            finish()
        }
    }
}