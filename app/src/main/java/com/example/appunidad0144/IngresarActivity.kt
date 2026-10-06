package com.example.appunidad0144

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appunidad0144.databinding.ActivityIngresarBinding
import com.example.appunidad0144.databinding.ActivityMainBinding

class IngresarActivity : AppCompatActivity() {

    private lateinit var b: ActivityIngresarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityIngresarBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(b.root)
        eventosClic()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun eventosClic(){
        b.btnIngresa.setOnClickListener {
            val usuario = b.txtUsuario.text.toString().trim()
            val pass = b.txtContra.text.toString().trim()

            // Usuario y contraseña obligatorios
            if (usuario.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Falto capturar información", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (usuario == getString(R.string.user) && pass == getString(R.string.paa)) {
                startActivity(Intent(this, CalculadoraActivity::class.java))
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT).show()
            }
        }

        b.btnSalir.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Calculadora")
            builder.setMessage("¿Desea salir?")

            builder.setPositiveButton("Aceptar"){
                    dialog, which -> finish()
            }

            builder.setNegativeButton("Cancelar"){
                    dialog, which ->
                Toast.makeText(this,"Continuamos en la app", Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }
    }
}