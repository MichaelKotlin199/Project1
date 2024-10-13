package org.example.figure

sealed class Figure(open val property: Double) {
    init {
        println(this.toString())
    }

    abstract fun getPerimeter(): Double

    abstract fun getArea(): Double
}