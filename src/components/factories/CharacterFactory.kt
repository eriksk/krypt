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
import components.characters.CharacterMovementController
import components.collision.WorldCollisionComponent
import world.generation.World
import components.characters.CharacterShadowComponent
import components.characters.HealthComponent
import components.characters.HealthDisplayComponent

/**
 * Created by Erik on 2014-12-27.
 */
class CharacterFactory(val content: ContentManager, val world: World) : EntityFactory {

    override fun create(): Entity {
        return create(0f, 0f)
    }

    override fun create(x: Float, y: Float): Entity {
        val entity = Entity("player")

        val animationTime = 75f

        val animations = hashMapOf(
                Pair("walk_south", FrameStepAnimation(array(0, 1, 2, 3), animationTime)),
                Pair("walk_west", FrameStepAnimation(array(4, 5, 6, 7), animationTime)),
                Pair("walk_east", FrameStepAnimation(array(8, 9, 10, 11), animationTime)),
                Pair("walk_north", FrameStepAnimation(array(12, 13, 14, 15), animationTime))
        )

        val atlas = TextureAtlas(16, content.load("gfx/DawnLike_3/Commissions/Paladin"))
        val guiAtlas = TextureAtlas(16, content.load("gfx/DawnLike_3/GUI/GUI0"))

        entity.addComponent(HealthComponent(entity, 100))
        entity.addComponent(CharacterShadowComponent(entity, content.load("gfx/shadow")))
        entity.addComponent(AnimationComponent(entity, animations))
        entity.addComponent(CharacterComponent(entity))
        entity.addComponent(CharacterRenderer(entity, atlas))
        entity.addComponent(AnimationInputControllerComponent(entity))
        entity.addComponent(CharacterMovementController(entity))
        entity.addComponent(WorldCollisionComponent(entity, world))
        entity.addComponent(HealthDisplayComponent(entity, guiAtlas))

        entity.transform.position.set(x, y)
        return entity
    }
}