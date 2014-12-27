package components.animation

import skoggy.ecs.Entity
import animations.FrameStepAnimation
import java.util.HashMap
import skoggy.ecs.Component
import skoggy.ecs.Updateable

/**
 * Created by erikskoglund on 2014-12-25.
 */
class AnimationComponent(entity: Entity, val animations: HashMap<String, FrameStepAnimation>) : Component(entity), Updateable {

    var currentAnimation = animations.values().first()

    public fun setAnim(name: String){
        if(currentAnimation == animations.get(name))
            return

        currentAnimation = animations.get(name)
        currentAnimation.reset()
    }

    public fun getCurrentFrame():Int{
        return currentAnimation.frame
    }

    override fun update(dt: Float) {
        currentAnimation.update(dt)
    }

}