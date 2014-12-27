package cameras

import skoggy.ecs.Transform
import org.newdawn.slick.Graphics
import org.newdawn.slick.geom.Vector2f
import krypt.lerp

/**
 * Created by Erik on 2014-12-27.
 */
class Camera(val width: Float, val height: Float){
    val transform = Transform()
    var target = Vector2f()
    val speed = 0.01f

    public fun move(target: Vector2f){
        this.target.x = target.x
        this.target.y = target.y
    }

    public fun update(dt: Float){
        transform.position.x = lerp(transform.position.x, target.x, speed)
        transform.position.y = lerp(transform.position.y, target.y, speed)
    }

    public fun lookThrough(graphics: Graphics?){
        graphics?.translate(width/2f, height/2f)
        graphics?.scale(transform.scale.x, transform.scale.y)
        graphics?.translate(-transform.position.x, -transform.position.y)
    }
}