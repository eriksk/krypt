package components.input

import skoggy.ecs.Entity
import skoggy.ecs.Component
import skoggy.ecs.Updateable
import components.animation.AnimationComponent
import org.lwjgl.input.Keyboard
import components.characters.CharacterMovementController
import world.Direction

/**
 * Created by erikskoglund on 2014-12-25.
 */
class AnimationInputControllerComponent(entity: Entity) : Component(entity), Updateable {

    override fun update(dt: Float) {

        val controller = getComponent(javaClass<CharacterMovementController>())

        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            controller.move(Direction.WEST, dt)
        }else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            controller.move(Direction.EAST, dt)
        }else if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            controller.move(Direction.NORTH, dt)
        }else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            controller.move(Direction.SOUTH, dt)
        }
    }
}