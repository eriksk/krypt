package components.factories

import ecs.EntityFactory
import skoggy.ecs.Entity
import animations.FrameStepAnimation
import sprites.TextureAtlas
import components.animation.AnimationComponent
import components.characters.CharacterComponent
import components.rendering.CharacterRenderer
import components.input.AnimationInputControllerComponent
import components.characters.CharacterMovementController
import content.ContentManager
import krypt.rand

/**
 * Created by Erik on 2014-12-28.
 */
class MobFactory(val content: ContentManager) : EntityFactory{

    var count = 0

    val mobTypes = hashMapOf<String, MobData>(
            Pair("Demon", MobData(8 * 9, 22)),
            Pair("Undead", MobData(8 * 10, 28)),
            Pair("Elemental", MobData(8 * 11, 14))
    )

    fun getRandomMobType(): String{
        val index = (mobTypes.size() * rand()).toInt()
        var i = 0
        for(type in mobTypes){
            if(i == index)
                return type.getKey()
            i++
        }
        return ""
    }

    override fun create(): Entity {
        return create(0f, 0f)
    }

    override fun create(x: Float, y: Float): Entity {
        count++

        val entity = Entity("mob_$count")

        val mobName = getRandomMobType()

        val mob = mobTypes.get(mobName)

        val animationTime = 300f + rand() * 200f
        val mobType = (mob.typeCount * rand()).toInt()
        val total = mob.total

        val animations = hashMapOf(
            Pair("idle", FrameStepAnimation(array(mobType, mobType + total), animationTime))
        )

        val atlas = TextureAtlas(16, content.load("gfx/DawnLike_3/Characters/$mobName"))

        entity.addComponent(AnimationComponent(entity, animations))
        entity.addComponent(CharacterComponent(entity))
        entity.addComponent(CharacterRenderer(entity, atlas))

        entity.transform.position.set(x, y)

        return entity
    }
}