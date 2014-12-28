package world.generation

import world.tiling.AutoTiler
import krypt.arrayOfZeroes
import world.Cell
import krypt.rand
import java.util.Random

/**
 * Created by erikskoglund on 2014-12-27.
 */
class World(val cellSize: Int, val width: Int, val height: Int, val autoTiler: AutoTiler){
    val size = width * height
    val data = arrayOfZeroes(size)

    val density : Float
        get(){
            var count = 0
            width.indices.forEach{ col ->
                height.indices.forEach { row ->
                    count += if(get(col, row) == 0) 1 else 0
                }
            }
            return count.toFloat() / (width * height).toFloat()
        }

    fun getCol(x: Float): Int {
        return (x / cellSize.toFloat()).toInt()
    }

    fun getRow(y: Float): Int {
        return (y / cellSize.toFloat()).toInt()
    }

    public fun getRandomCell(index: Int, random: Random):Cell{
        while(true){
            val col = (random.nextFloat() * width.toFloat()).toInt()
            val row = (random.nextFloat() * height.toFloat()).toInt()
            if(get(col, row) == index){
                return Cell(col, row)
            }
        }
    }

    public fun getSafe(col: Int, row: Int, default: Int): Int{
        if(col < 0 || row < 0 || col > width - 1 || row > height - 1)
            return default
        return data[col + row * width]
    }

    public fun get(col: Int, row: Int): Int{
        validateCell(col, row)

        return data[col + row * width]
    }

    public fun set(col: Int, row: Int, value: Int){
        validateCell(col, row)

        data[col + row * width] = value
    }

    fun validateCell(col: Int, row: Int){
        if(col < 0 || row < 0 || col > width - 1 || row > height - 1)
            throw IndexOutOfBoundsException("Cell doesn't exist")
    }
}