package com.example.appunidad0144

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.appunidad0144.databinding.ActivityClienteBinding
import com.example.appunidad0144.databinding.ActivityMainBinding

class ClienteActivity : AppCompatActivity() {
    private lateinit var txtCliente: EditText
    private lateinit var btnEntrar: Button
    private lateinit var btnRegresar: Button

    private lateinit var b: ActivityClienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityClienteBinding.inflate(layoutInflater)
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

       b.btnEntrar.setOnClickListener {
            if (b.txtCliente.text.toString().contentEquals("")){
                Toast.makeText(this, "Falto Capturar Informarción",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
           val intent= Intent(this, CotizacionActivity::class.java)
           intent.putExtra("cliente", b.txtCliente.text.toString())
           startActivity(intent)
        }

        b.btnRegresar.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("App Cotización")
            builder.setMessage("¿Desea salir?")

            //botones
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