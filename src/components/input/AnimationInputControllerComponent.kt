package components.input

import skoggy.ecs.Entity
import skoggy.ecs.Component
import skoggy.ecs.Updateable
import components.animation.AnimationComponent
import org.lwjgl.input.Keyboard

/**
 * Created by erikskoglund on 2014-12-25.
 */
class AnimationInputControllerComponent(entity: Entity) : Component(entity), Updateable {

    override fun update(dt: Float) {

        val anims = getComponent(javaClass<AnimationComponent>())

        val speed = 0.09f

        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            anims.setAnim("walk_west")
            transform.position.x -= speed * dt
        }else if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            anims.setAnim("walk_east")
            transform.position.x += speed * dt
        }else if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            anims.setAnim("walk_north")
            transform.position.y -= speed * dt
        }else if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
            anims.setAnim("walk_south")
            transform.position.y += speed * dt
        }
    }

}