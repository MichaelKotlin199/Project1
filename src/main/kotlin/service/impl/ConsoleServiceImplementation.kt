package org.example.service.implementation

import org.example.Operation
import org.example.exception.BadPropertyException
import org.example.exception.WrongOperationTypeException
import org.example.service.ConsoleService

object ConsoleServiceImplementation : ConsoleService {
    private fun getOperation(operationNumber: Int): Operation {
        return when (operationNumber) {
            1 -> Operation.INSERT
            2 -> Operation.GET_AREA
            3 -> Operation.GET_PERIMETER
            4 -> Operation.EXIT
            else -> throw IllegalArgumentException("Invalid operation number")
        }
    }

    private fun addFigure() {
        println("Adding Figure, 0 -- Circle, 1 -- Square:")
        val number: Int = readln().toInt()
        if (number !in intArrayOf(0, 1)) {
            throw WrongOperationTypeException("Invalid number")
        }
        println("Write property: ")
        val property: Double = readln().toDouble()
        if (property < 0) {
            throw BadPropertyException("Property must be positive.")
        }
        when (number) {
            0 -> FigureServiceImplementation.addCircle(property)
            1 -> FigureServiceImplementation.addSquare(property)
        }
    }

    override fun work() {
        while(true) {
            println("Введите тип операции, которую хотите исполнить:\n1) добавить фигуру\n2) получить площадь всех фигур\n3) получить периметр всех фигур\n4) завершить выполнение")
            try {
                val operation = getOperation(readln().toInt())
                when (operation) {
                    Operation.INSERT -> addFigure()
                    Operation.GET_AREA -> println(FigureServiceImplementation.getArea())
                    Operation.GET_PERIMETER -> println(FigureServiceImplementation.getPerimeter())
                    Operation.EXIT -> break
                }
            }
            catch (e: Exception) {
                println(e)
            }
        }
    }
}