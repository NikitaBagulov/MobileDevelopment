import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

class Square(var x: Int, var y: Int, var side: Int): Figure(0), Movable, Transforming  {
    var color: Int = -1 // при объявлении каждое поле нужно инициализировать

    lateinit var name: String // значение на момент определения неизвестно (только для объектных типов)
    // дополнительный конструктор вызывает основной
    constructor(square: Square) : this(square.x, square.y, square.side)

    override fun toString(): String {
        return "Square: ($x, $y), side ($side)"
    }

    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }
    override fun area(): Float {
        return  (side*side).toFloat();
    }

    override fun resize(zoom: Int) {
        side*=zoom
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