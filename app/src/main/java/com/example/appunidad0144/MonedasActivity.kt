package com.example.appunidad0144

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MonedasActivity : AppCompatActivity() {

    private lateinit var txtCantidad: EditText
    private lateinit var spnMoneda: Spinner
    private lateinit var txtResultado: TextView

    private lateinit var btnConvertir: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnSalir: Button

    private var pos: Int = 0    // o -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_monedas)
        iniciarComponentes()
        eventos()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun iniciarComponentes(){

        txtCantidad= findViewById(R.id.txtCantidad)
        txtResultado= findViewById(R.id.txtResultado)
        spnMoneda= findViewById(R.id.spnMonedas)
        btnConvertir= findViewById(R.id.btnConvertir)
        btnLimpiar= findViewById(R.id.btnLimpiar)
        btnSalir= findViewById(R.id.btnSalir)

        //obtener los items del array-string par aponerlos en un objeto Adaptador
        val items = resources.getStringArray(R.array.strMonedas)
        val adapter= ArrayAdapter(this, android.R.layout.simple_list_item_1,items)
        spnMoneda.adapter = adapter
    }


    fun eventos(){

        spnMoneda.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                pos = position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        btnConvertir.setOnClickListener {

            //validación

            if (txtCantidad.text.toString().contentEquals("") || txtCantidad.text.toString().toFloat() <=0){
                Toast.makeText(
                    this, "Falto seleccionar capturar información o la cantidad es negativa o 0",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            //obtener los valores de las monedas
            val dolarA : Float = getString(R.string.strDolarA).toFloat()
            val dolaC : Float = getString(R.string.strDolaC).toFloat()
            val euro: Float = getString(R.string.strEuro).toFloat()
            val libra : Float = getString(R.string.strLibra).toFloat()
            val cantidad: Float = txtCantidad.text.toString().toFloat()


            if (pos == -1){
                Toast.makeText(this, "Elige una opción para continuar", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            //converción

            val resultado = when(pos){
                0-> cantidad/dolarA
                1-> cantidad/dolaC
                2-> cantidad/euro
                3-> cantidad/libra
                else -> 0.0f
            }
            txtResultado.text = String.format("%.2f",resultado)


        }


            btnLimpiar.setOnClickListener{
                txtCantidad.setText("")
                txtResultado.setText("")
                spnMoneda.setSelection(0)
                pos=0
            }


        btnSalir.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Monedas")
            builder.setMessage("¿Desea salir?")

            //botones
            builder.setPositiveButton("Aceptar"){
                    dialog, which -> finish()
            }

            builder.setNegativeButton("Cancelar"){
                    dialog, whitch ->
                Toast.makeText(this,"Continuamos en Monedas", Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }

    }
}