package components.characters

import skoggy.ecs.Entity
import skoggy.ecs.Component
import components.animation.AnimationComponent
import org.lwjgl.input.Keyboard
import skoggy.ecs.Updateable
import world.Direction

/**
 * Created by Erik on 2014-12-28.
 */
class CharacterMovementController(entity:Entity) : Component(entity), Updateable{

    val speed = 0.09f
    var moving = false

    public fun move(direction:Direction, dt: Float) {
        val anims = getComponent(javaClass<AnimationComponent>())

        if(direction == Direction.WEST){
            anims.setAnim("walk_west")
            transform.position.x -= speed * dt
            moving = true
        }else if(direction == Direction.EAST){
            anims.setAnim("walk_east")
            transform.position.x += speed * dt
            moving = true
        }else if(direction == Direction.NORTH){
            anims.setAnim("walk_north")
            transform.position.y -= speed * dt
            moving = true
        }else if(direction == Direction.SOUTH){
            anims.setAnim("walk_south")
            transform.position.y += speed * dt
            moving = true
        }
    }


    override fun update(dt: Float) {
        if(!moving){
            val anims = getComponent(javaClass<AnimationComponent>())
            anims.currentAnimation.reset()
        }

        moving = false
    }
}