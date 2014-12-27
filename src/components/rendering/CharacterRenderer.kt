package components.rendering

import skoggy.ecs.Component
import skoggy.ecs.Entity
import skoggy.ecs.Renderable
import sprites.TextureAtlas
import components.animation.AnimationComponent
import org.newdawn.slick.Image
import org.newdawn.slick.Color

/**
 * Created by Erik on 2014-12-27.
 */
class CharacterRenderer(entity: Entity, val atlas: TextureAtlas) : Component(entity), Renderable{

    val image = Image(atlas.texture)

    override fun onStart(){
        image.setFilter(Image.FILTER_NEAREST)
    }

    override fun render() {
        val frame = getComponent(javaClass<AnimationComponent>()).currentAnimation.frame
        val source = atlas.get(frame)

        val halfWidth = source.width * transform.scale.x / 2f
        val halfHeight = source.height * transform.scale.y / 2f

        val color = Color.white

        image.setRotation(transform.rotation * 180f / 6.28f)
        image.draw(
                transform.position.x - halfWidth, transform.position.y - halfHeight,
                transform.position.x + halfWidth, transform.position.y + halfHeight,
                source.x, source.y, source.x2, source.y2,
                color)

    }
}