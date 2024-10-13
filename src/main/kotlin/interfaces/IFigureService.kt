package org.example.interfaces

interface IFigureService {
    fun addSquare(property: Double)
    fun addCircle(property: Double)
    fun getPerimeter(): List<Double>
    fun getArea(): List<Double>
}