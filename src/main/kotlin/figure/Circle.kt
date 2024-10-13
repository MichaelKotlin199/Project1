package org.example.figure

import kotlin.math.PI

data class Circle(override val property: Double) : Figure(property) {
    override fun getPerimeter(): Double {
        return 2 * PI * property
    }

    override fun getArea(): Double {
        return PI * property * property
    }
}
