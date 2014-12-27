package world.generation

import world.Cell
import java.util.ArrayList
import krypt.rand

/**
 * Created by erikskoglund on 2014-12-27.
 */
class Digger(col : Int, row : Int){
    var position = Cell(col, row)
    val directions = arrayListOf(
        Cell(-1, 0),
        Cell(1, 0),
        Cell(0, -1),
        Cell(0, 1)
    )
    var direction = getRandomDirection()

    var done = false
    var stepsTaken = 0

    fun getRandomDirection():Cell{
        return directions[(directions.size() * rand()).toInt()]
    }

    fun directionValid(world :World, dir: Cell):Boolean{
        return world.getSafe(position.col + dir.col, position.row + dir.row) != 0
    }

    public fun update(world: World, turnChance: Float){

        if(rand() > turnChance) {
            direction = getRandomDirection()
        }

        if(directions.count { !directionValid(world, it) } > 2){
            done = true
            return
        }

        while(!directionValid(world, direction)){
            direction = getRandomDirection()
        }

        world.set(position.col + direction.col, position.row + direction.row, 0)
        position.col += direction.col
        position.row += direction.row
        stepsTaken += 1

    }
}