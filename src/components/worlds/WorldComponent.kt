package components.worlds

import skoggy.ecs.Component
import skoggy.ecs.Entity
import world.generation.World

/**
 * Created by Erik on 2014-12-27.
 */
class WorldComponent(entity:Entity, val world:World) : Component(entity){
}