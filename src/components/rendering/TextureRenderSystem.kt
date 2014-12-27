package components.rendering

import skoggy.ecs.Entity
import org.newdawn.slick.opengl.Texture
import skoggy.ecs.Renderable
import components.animation.AnimationComponent
import org.newdawn.slick.Image
import org.newdawn.slick.geom.Rectangle

/**
 * Created by erikskoglund on 2014-12-25.
 */
class TextureRenderSystem(entity: Entity, val texture: Texture) : ecs.Component(entity), Renderable {

    val img = Image(texture)

    override fun onStart(){
        img.setRotation(transform.rotation / 180f * 6.28f)
        img.setFilter(Image.FILTER_NEAREST)
    }

    override fun render() {
        var source = Rectangle(0f, 0f, 0f, 0f)

        var cell = getComponent(javaClass<AnimationComponent>()).getCurrentFrame()

        var col = cell % 4
        var row = cell / 4

        // TODO: take a spritesheet in constructor instead

        source.setBounds(col * 16f, row * 16f, 16f, 16f)

        val halfWidth = source.getWidth() / 2f
        val halfHeight = source.getHeight() / 2f

        // batch?
        img.startUse()

        img.drawEmbedded(
                transform.position.x - halfWidth * transform.scale.x, transform.position.y - halfHeight * transform.scale.y,
                transform.position.x + halfWidth * transform.scale.x, transform.position.y + halfHeight * transform.scale.y,
                source.getX(), source.getY(), source.getX() + source.getWidth(), source.getY() + source.getHeight())

        img.endUse()
    }

}