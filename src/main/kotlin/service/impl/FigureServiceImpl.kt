package org.example.service.implementation

import org.example.figure.Circle
import org.example.figure.Figure
import org.example.figure.Square
import org.example.service.FigureService

object FigureServiceImpl : FigureService {
    private val figures: MutableList<Figure> = mutableListOf()

    override fun addSquare(property: Double) {
        figures.add(Square(property))
    }
    override fun addCircle(property: Double) {
        figures.add(Circle(property))
    }
    override fun getPerimeter(): List<Double> {
        return  figures.map { figure -> figure.getPerimeter() }
    }
    override fun getArea(): List<Double> {
        return figures.map { figure -> figure.getArea() }
    }
}