package org.example

import kotlin.math.PI

class BadPropertyException(message: String) : Exception(message)

class WrongOperationTypeException(message: String) : Exception(message)

enum class Operation {
    INSERT,
    GET_AREA,
    GET_PERIMETER,
    EXIT,
}

internal open class Figure(val property: Double) {
    init {
        println(this.toString())
    }

    override fun toString(): String {
        return "${this::class.simpleName}(property=$property)"
    }

    open fun getPerimeter(): Double {
        return 0.0
    }

    open fun getArea(): Double {
        return 0.0
    }
}

internal class Circle(property: Double) : Figure(property) {
    override fun getPerimeter(): Double {
        return 2 * PI * property
    }

    override fun getArea(): Double {
        return PI * property * property
    }
}

internal class Square(property: Double) : Figure(property) {
    override fun getPerimeter(): Double {
        return property * 4
    }

    override fun getArea(): Double {
        return property * property
    }
}

interface ConsoleService {
    fun work()
}

interface FigureService {
    fun addSquare(property: Double)
    fun addCircle(property: Double)
    fun getPerimeter(): List<Double>
    fun getArea(): List<Double>
}

class FigureServiceImpl : FigureService {
    private var figures: MutableList<Figure> = mutableListOf()

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

fun getOperation(operationNumber: Int): Operation {
    return when (operationNumber) {
        1 -> Operation.INSERT
        2 -> Operation.GET_AREA
        3 -> Operation.GET_PERIMETER
        4 -> Operation.EXIT
        else -> throw IllegalArgumentException("Invalid operation number")
    }
}

class ConsoleServiceImpl(private var service: FigureService) : ConsoleService {

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
            0 -> service.addCircle(property)
            1 -> service.addSquare(property)
        }
    }

    override fun work() {
        while(true) {
            println("Введите тип операции, которую хотите исполнить:\n1) добавить фигуру\n2) получить площадь всех фигур\n3) получить периметр всех фигур\n4) завершить выполнение")
            try {
                val operation = getOperation(readln().toInt())
                when (operation) {
                    Operation.INSERT -> addFigure()
                    Operation.GET_AREA -> println(service.getArea())
                    Operation.GET_PERIMETER -> println(service.getPerimeter())
                    Operation.EXIT -> break
                }
            }
            catch (e: Exception) {
                println(e)
            }
        }
    }
}

object FigureServiceSingleton {
    val instance: FigureService by lazy { FigureServiceImpl() }
}

object ConsoleServiceSingleton {
    val instance: ConsoleService by lazy { ConsoleServiceImpl(FigureServiceSingleton.instance) }
}

fun main() {
    val c = ConsoleServiceSingleton.instance
    c.work()
}