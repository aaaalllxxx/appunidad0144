package com.example.appunidad0144

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    private lateinit var crvHola: CardView
    private lateinit var crvImc: CardView
    private lateinit var crvConGrados: CardView
    private lateinit var crvMoneda: CardView
    private lateinit var crvCotizacion: CardView
    private lateinit var crvSalir: CardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)

        iniciarComponentes()
        eventosClick()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

    }
    fun iniciarComponentes(){
        crvHola= findViewById(R.id.crvHola)
        crvImc = findViewById(R.id.crvImc)
        crvConGrados= findViewById(R.id.crvGrados)
        crvMoneda= findViewById(R.id.crvMonedas)
        crvCotizacion= findViewById(R.id.crvCotizacion)
        crvSalir= findViewById(R.id.crvSalir)

    }

    fun eventosClick(){
        crvHola.setOnClickListener {

            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
        crvImc.setOnClickListener {
            var intent = Intent(this, ImcActivity::class.java )
            startActivity(intent)
        }
        crvConGrados.setOnClickListener{
            var intent= Intent(this, GradosActivity::class.java)
            startActivity(intent)
        }
        crvMoneda.setOnClickListener{
            var intent= Intent(this, MonedasActivity::class.java)
            startActivity(intent)
        }
        crvSalir.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Hola")
            builder.setMessage("¿Desea salir?")

            //botones
            builder.setPositiveButton("Aceptar"){
                    dialog, which -> finish()
            }

            builder.setNegativeButton("Cancelar"){
                    dialog, whitch ->
                Toast.makeText(this,"Continuamos en la app", Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }
    }
}