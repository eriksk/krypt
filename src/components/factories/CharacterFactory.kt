package components.factories

import ecs.EntityFactory
import skoggy.ecs.Entity
import components.characters.CharacterComponent
import components.animation.AnimationComponent
import animations.FrameStepAnimation
import components.rendering.CharacterRenderer
import sprites.TextureAtlas
import content.ContentManager
import components.input.AnimationInputControllerComponent

/**
 * Created by Erik on 2014-12-27.
 */
class CharacterFactory(val content: ContentManager) : EntityFactory {

    override fun create(): Entity {
        val entity = Entity("player")

        val animations = hashMapOf(
                Pair("walk_south", FrameStepAnimation(array(0, 1, 2, 3), 100f)),
                Pair("walk_west", FrameStepAnimation(array(4, 5, 6, 7), 100f)),
                Pair("walk_east", FrameStepAnimation(array(8, 9, 10, 11), 100f)),
                Pair("walk_north", FrameStepAnimation(array(12, 13, 14, 15), 100f))
        )

        val atlas = TextureAtlas(16, content.load("gfx/DawnLike_3/Commissions/Paladin"))

        entity.addComponent(AnimationComponent(entity, animations))
        entity.addComponent(CharacterComponent(entity))
        entity.addComponent(CharacterRenderer(entity, atlas))
        entity.addComponent(AnimationInputControllerComponent(entity))

        return entity
    }
}