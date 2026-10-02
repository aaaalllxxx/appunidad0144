package com.example.appunidad0144

class Rectangulo {
    var base: Float = 0f
    var altura: Float = 0f

    fun calculoArea(): Float = base * altura
    fun calculoPerimetro(): Float = 2 * (base + altura)
}