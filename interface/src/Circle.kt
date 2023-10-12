import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// TODO: дополнить определение класса размерами и позицией
class Circle(var x: Int, var y: Int, var r: Int) : Figure(0), Movable, Transforming {
    // TODO: реализовать интерфейс Transforming
    var color: Int = -1 // при объявлении каждое поле нужно инициализировать

    lateinit var name: String // значение на момент определения неизвестно (только для объектных типов)
    // дополнительный конструктор вызывает основной
    constructor(circle: Circle) : this(circle.x, circle.y, circle.r)

    override fun toString(): String {
        return "Circle: ($x, $y), radius ($r)"
    }

    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }
    override fun area(): Float {
        return  (PI*r*r).toFloat();
    }

    override fun resize(zoom: Int) {
        r*=zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        when(direction){
            RotateDirection.Clockwise -> {
                val dx = x - centerX
                val dy = y - centerY
                x = centerX + dy
                y = centerY - dx
            }
            RotateDirection.CounterClockwise -> {
                val dx = x - centerX
                val dy = y - centerY
                x = centerX - dy
                y = centerY + dx
            }
        }
    }

}