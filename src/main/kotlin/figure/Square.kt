package org.example.figure

data class Square(override val property: Double) : Figure(property) {
    override fun getPerimeter(): Double {
        return property * 4
    }

    override fun getArea(): Double {
        return property * property
    }
}
