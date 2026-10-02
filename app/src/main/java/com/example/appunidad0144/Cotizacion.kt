package com.example.appunidad0144

import java.io.Serializable
import kotlin.random.Random
import kotlin.random.asJavaRandom

class Cotizacion: Serializable {

    var numCotizacion: Int=0
    var descripcion: String= ""
    var porPagoInc: Float= 0.0f
    var precio: Float= 0.0f
    var plazos: Int= 0
    fun calcularPagoInicial(): Float{

        return precio* (porPagoInc/100)
    }
    fun calcularTotalFin(): Float{

        return precio- calcularPagoInicial()
    }

    fun calcularMensual(): Float{
        return calcularTotalFin()/plazos
    }

    fun generarNumCotizacion(): Int {
        return (1..100).random()
    }
}





