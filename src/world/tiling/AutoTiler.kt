package world.tiling

import world.generation.World

/**
 * Created by erikskoglund on 2014-12-27.
 */
class AutoTiler(){

    public open fun autoTile(world: World){
        world.width.indices.forEach { col ->
            world.height.indices.forEach { row ->
                if(world.get(col, row) != 0)
                    autoTileCell(world, col, row)
            }
        }
    }

    fun autoTileCell(world: World, col: Int, row: Int) {
        var sum = 0

        sum += if(world.getSafe(col, row - 1, 999) == 0) 1 else 0
        sum += if(world.getSafe(col + 1, row, 999) == 0) 2 else 0
        sum += if(world.getSafe(col, row + 1, 999) == 0) 4 else 0
        sum += if(world.getSafe(col - 1, row, 999) == 0) 8 else 0

        if(sum == 0)
            sum = 16

        world.set(col, row, sum)
    }
}