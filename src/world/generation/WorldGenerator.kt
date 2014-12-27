package world.generation

import world.tiling.AutoTiler
import kotlin.concurrent.currentThread
import java.util.Random

/**
 * Created by erikskoglund on 2014-12-27.
 */
class WorldGenerator(val world: World){

    val MAX_DIGGERS = 200 // good to use as a size for the map
    val SPAWN_DIGGER_AFTER_STEPS = 6
    val DIGGER_TURN_CHANCE = 0.4f

    val CELL_FLOOR = 0
    val CELL_WALL = 1

    var random = Random()

    public fun generate(seed: Int){
        random = Random(seed.toLong())

        clear()
        digRooms()
        autoTile()
    }

    fun clear(){
        world.data.indices.forEach { world.data[it] = CELL_WALL }
    }

    fun digRooms(){

        var totalDiggers = 1
        val maxDiggers = MAX_DIGGERS

        val diggers = arrayListOf<Digger>(
            Digger(world.width / 2, world.height / 2, random),
            Digger(world.width / 2, world.height / 2, random)
        )

        while(diggers.size() > 0) {
            var i = 0
            while(i < diggers.size()){
                val digger = diggers.get(i)
                digger.update(world, DIGGER_TURN_CHANCE)
                if(digger.stepsTaken > SPAWN_DIGGER_AFTER_STEPS && totalDiggers < maxDiggers) {
                    digger.stepsTaken = 0
                    diggers.add(Digger(digger.position.col, digger.position.row, random))
                    totalDiggers += 1
                    println("Current diggers: ${diggers.size()}")
                }
                i++
            }

            autoTile()

            i = 0
            while(i < diggers.size()) {
                if(diggers.get(i).done)
                    diggers.remove(i--)
                i++
            }

            Thread.sleep(10)
            //        Thread.sleep(0.0001)

        }


        println("Tick")

        world.set(world.width / 2, world.height / 2, 0)

        println("Total diggers: $totalDiggers")
    }

    fun autoTile(){
        world.autoTiler.autoTile(world)
    }
}