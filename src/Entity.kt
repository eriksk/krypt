package skoggy

import org.newdawn.slick.opengl.Texture
import org.newdawn.slick.geom.Vector2f
import org.newdawn.slick.geom.Rectangle
import org.newdawn.slick.Image
import org.newdawn.slick.Color
import skoggy.ecs.Transform

/**
 * Created by erikskoglund on 2014-12-25.
 */
class Entity(val texture: Texture){

    private val img = Image(texture)

    public val transform: Transform = Transform()
    public val source: Rectangle = Rectangle(0f, 0f, texture.getTextureWidth() * 1f, texture.getTextureHeight() * 1f)
    public val color: Color = Color(1f, 1f, 1f, 1f)

    open fun update(dt: Float){}

    open fun draw(){
        img.setRotation(transform.rotation * 180f / 6.28f)
        img.draw(
                transform.position.x, transform.position.y,
                source.getX(), source.getY(), source.getX() + source.getWidth(), source.getY() + source.getHeight())
    }
}