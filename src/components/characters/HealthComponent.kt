package components.characters

import skoggy.ecs.Component
import skoggy.ecs.Entity

/**
 * Created by Erik on 2014-12-28.
 */
class HealthComponent(entity: Entity, val maxHealth: Int) : Component(entity){

    var health = maxHealth

    val percentage : Float
        get()= health.toFloat() / maxHealth.toFloat()

    public fun heal(amount: Int){
        health += amount
        clamp()
    }

    public fun deal(amount: Int){
        health -= amount
        clamp()
    }

    fun clamp() {
        if(health < 0)
            health = 0
        if(health > maxHealth)
            health = maxHealth
    }
}