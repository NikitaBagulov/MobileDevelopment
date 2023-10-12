fun main() {
    val circle = Circle(0, 0, 3)
    val rectangle = Rect(0,0,5, 2)
    val square = Square(0, 0, 4)
    println("Наши фигуры")
    println(circle)
    println(rectangle)
    println(square)
    println("\nПлощадь фигур")
    println("circle area: " + circle.area())
    println("rectangle area: "+ rectangle.area())
    println("square area: "+ square.area())
    println("\nПеремещение к точке (1,1) и увеличение фигур в 2 раза")
    circle.move(1, 1)
    rectangle.move(1, 1)
    square.move(1, 1)

    circle.resize(2)
    rectangle.resize(2)
    square.resize(2)

    println(circle)
    println(rectangle)
    println(square)
    println("\nПоворот фигур")
    circle.rotate(RotateDirection.Clockwise, 0, 0)
    rectangle.rotate(RotateDirection.CounterClockwise, 0, 0)
    square.rotate(RotateDirection.Clockwise, 1, 0)

    println("Поворот круга по часовой относительно точки (0, 0): "+circle)
    println("Поворот треугольника против часовой отночительно (0, 0): "+rectangle)
    println("Поворот квадрата по часовой относительно (1, 0): "+square)

}