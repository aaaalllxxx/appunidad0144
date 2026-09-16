package com.example.appunidad0144

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ImcActivity : AppCompatActivity() {
    private lateinit var txtAltura : EditText
    private lateinit var txtPeso : EditText
    private lateinit var txtRes: TextView

    private lateinit var imgCateg: ImageView
    private lateinit var btnCalcular : Button
    private lateinit var btnLimpiar : Button
    private lateinit var btnSalir: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_imc)
        iniciarComponentes()
        eventosClic()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun iniciarComponentes(){


        txtAltura= findViewById(R.id.txtAltura)
        txtPeso= findViewById(R.id.txtPeso)
        txtRes = findViewById(R.id.txtResultado)

        imgCateg = findViewById(R.id.imgCategorias)
        btnCalcular = findViewById(R.id.btnCalcular)
        btnLimpiar = findViewById(R.id.btnLimpiar)
        btnSalir = findViewById(R.id.btnSalir)
    }

    fun eventosClic(){

        btnCalcular.setOnClickListener {

            //validar información

            if (
                txtAltura.text.toString().contentEquals("") ||
                txtPeso.text.toString().contentEquals("") ||
                txtPeso.text.toString().toFloat() <=0 ||
                txtAltura.text.toString().toFloat() <=0){

                Toast.makeText(
                    this, "Falto capturar información o es un valor invalido",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener }

                //calcular imc
                var altura : Float = txtAltura.text.toString().toFloat()
                var peso : Float = txtPeso.text.toString().toFloat()
                var imc: Float = 0.0f

                imc= peso/(altura*altura)

                txtRes.text = String.format("%.2f",imc)

                //buscar categoria

                if(imc< 18.5)imgCateg.setImageResource(R.mipmap.bajopeso)
                if(imc >= 18.5 && imc < 24.9 ) imgCateg.setImageResource(R.mipmap.pesonormal)
                if (imc >= 25 && imc < 29.9) imgCateg.setImageResource(R.mipmap.sobrepeso)
                if(imc >= 30 && imc < 34.9) imgCateg.setImageResource(R.mipmap.obesidad)

            }

            btnLimpiar.setOnClickListener {
                txtAltura.setText("")
                txtPeso.setText("")
                txtRes.setText("IMC =")
                imgCateg.setImageResource(R.mipmap.categorias)
            }

            btnSalir.setOnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("App Imc")
                builder.setMessage("¿Desea salir?")

                //botones
                builder.setPositiveButton("Aceptar"){
                        dialog, which -> finish()
                }

                builder.setNegativeButton("Cancelar"){
                        dialog, whitch ->
                    Toast.makeText(this,"Continuamos en imc", Toast.LENGTH_SHORT).show()
                }

                builder.show()
            }

        }

    }
