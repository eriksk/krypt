package components.worlds

import world.generation.WorldGenerator
import skoggy.ecs.Entity
import skoggy.ecs.Component
import kotlin.concurrent.thread

/**
 * Created by Erik on 2014-12-27.
 */
class WorldGeneratorComponent(entity:Entity, val generator: WorldGenerator) : Component(entity){

    private var generateThread : Thread? = null

    public fun generate(seed: Int){
        generateThread?.interrupt()
        //generateThread = thread(){
            generator.generate(seed)
        //}
    }
}