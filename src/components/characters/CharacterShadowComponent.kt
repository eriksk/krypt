package components.characters

import skoggy.ecs.Entity
import skoggy.ecs.Component
import org.newdawn.slick.opengl.Texture
import skoggy.ecs.Renderable
import krypt.renderImage
import org.newdawn.slick.Image
import org.lwjgl.util.vector.Vector2f

/**
 * Created by Erik on 2014-12-28.
 */
class CharacterShadowComponent(entity:Entity, texture:Texture) : Component(entity), Renderable{

    val image = Image(texture)
    val color = krypt.color(0, 0, 0, 100)

    override fun onStart(){
        image.setFilter(Image.FILTER_NEAREST)
    }

    override fun render() {
        renderImage(image, Vector2f(transform.position.x, transform.position.y + 8f), 0f, Vector2f(1f, 1f), color)
    }

}