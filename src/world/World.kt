package world.generation

import world.tiling.AutoTiler
import krypt.arrayOfZeroes

/**
 * Created by erikskoglund on 2014-12-27.
 */
class World(val width: Int, val height: Int, val autoTiler: AutoTiler){
    val size = width * height
    val data = arrayOfZeroes(size)

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