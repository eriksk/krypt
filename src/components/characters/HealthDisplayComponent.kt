package components.characters

import skoggy.ecs.Entity
import skoggy.ecs.Component
import skoggy.ecs.Renderable
import sprites.TextureAtlas
import org.lwjgl.util.vector.Vector2f

/**
 * Created by Erik on 2014-12-28.
 */
class HealthDisplayComponent(entity: Entity, guiAtlas: TextureAtlas) : Component(entity), Renderable{

    val healthBar = guiAtlas.get(6, 3, 1)
    val healthFill = guiAtlas.get(22)

    val scale = 0.6f

    override fun render() {
        val health = getComponent(javaClass<HealthComponent>()).percentage

        healthFill.draw(Vector2f(transform.position.x, transform.position.y - 16), 0f, Vector2f(3f * scale * health, 1f * scale))
        healthBar.draw(Vector2f(transform.position.x, transform.position.y - 16), 0f, Vector2f(1f * scale, 1f * scale))
    }
}