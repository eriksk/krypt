package components.collision

import skoggy.ecs.Component
import skoggy.ecs.Entity
import world.generation.World
import skoggy.ecs.Updateable
import org.lwjgl.util.vector.Vector2f

/**
 * Created by Erik on 2014-12-28.
 */
class WorldCollisionComponent(entity:Entity, val world: World) : Component(entity), Updateable{

    val prev = Vector2f(0f, 0f)

    override fun onStart(){
        prev.x = transform.position.x
        prev.y = transform.position.y
    }

    override fun update(dt: Float) {
        val col = world.getCol(transform.position.x)
        val row = world.getRow(transform.position.y + 8f)

        if(world.getSafe(col, row, 0) != 0){
            transform.position.set(prev.x, prev.y)
        }

        prev.x = transform.position.x
        prev.y = transform.position.y
    }
}