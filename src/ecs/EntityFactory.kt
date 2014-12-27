package ecs

import skoggy.ecs.Entity

/**
 * Created by Erik on 2014-12-27.
 */
trait EntityFactory{
    fun create(): Entity
}