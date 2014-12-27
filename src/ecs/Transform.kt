package skoggy.ecs

import org.newdawn.slick.geom.Vector2f

/**
 * Created by erikskoglund on 2014-12-25.
 */
data class Transform{
    public var position: Vector2f = Vector2f()
    public var scale: Vector2f = Vector2f(1f, 1f)
    public var rotation: Float = 0f
}