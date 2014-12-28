package components.factories

import ecs.EntityFactory
import skoggy.ecs.Entity
import world.generation.World
import world.tiling.AutoTiler
import world.generation.WorldGenerator
import kotlin.concurrent.thread
import components.worlds.WorldComponent
import components.rendering.WorldRenderComponent
import world.rendering.WorldRenderer
import content.ContentManager
import sprites.TextureAtlas
import components.worlds.WorldGeneratorComponent

/**
 * Created by Erik on 2014-12-27.
 */
class WorldFactory(val content: ContentManager) : EntityFactory{

    override fun create(): Entity {
        return create(0f, 0f)
    }

    override fun create(x: Float, y: Float): Entity {
        val entity = Entity("world")

        val world = World(64, 64, AutoTiler())
        val renderer = WorldRenderer(TextureAtlas(16, content.load("gfx/DawnLike_3/Objects/Wall")))
        renderer.load()
        val generator = WorldGenerator(world)

        val generatorComponent = WorldGeneratorComponent(entity, generator)

        entity.addComponent(generatorComponent)
        entity.addComponent(WorldComponent(entity, world))
        entity.addComponent(WorldRenderComponent(entity, renderer))

        generatorComponent.generate(0)

        return entity
    }

}