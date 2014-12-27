package components.rendering

import skoggy.ecs.Component
import skoggy.ecs.Entity
import skoggy.ecs.Renderable
import world.rendering.WorldRenderer
import components.worlds.WorldComponent

/**
 * Created by Erik on 2014-12-27.
 */
class WorldRenderComponent(entity: Entity, val renderer: WorldRenderer) : Component(entity), Renderable{

    override fun render() {
        val world = getComponent(javaClass<WorldComponent>()).world

        renderer.render(world)
    }
}