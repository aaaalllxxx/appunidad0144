package com.example.appunidad0144

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appunidad0144.databinding.ActivityPreExamBinding

class PreExamActivity : AppCompatActivity() {

    private lateinit var b: ActivityPreExamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityPreExamBinding.inflate(layoutInflater)
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
        b.strIngresa.setOnClickListener {
            val usuario = b.txtUser.text.toString().trim()
            val pass = b.txtPass.text.toString().trim()

            // Usuario y contraseña obligatorios
            if (usuario.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Falto capturar información", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }


            if (usuario == getString(R.string.user) && pass == getString(R.string.paa)) {
                startActivity(Intent(this, RectanguloActivity::class.java))
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos",
                    Toast.LENGTH_SHORT).show()
            }
        }

        b.strCerrarPreEx.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App PreExam")
            builder.setMessage("¿Desea salir?")


            builder.setPositiveButton("Aceptar"){
                    dialog, which -> finish()
            }

            builder.setNegativeButton("Cancelar"){
                    dialog, whitch ->
                Toast.makeText(this,"Continuamos en cotización", Toast.LENGTH_SHORT).show()
            }

            builder.show()
        }
    }
}