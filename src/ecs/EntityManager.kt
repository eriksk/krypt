package skoggy.ecs

/**
 * Created by erikskoglund on 2014-12-25.
 */
class EntityManager{
    val entities = arrayListOf<Entity>()

    public fun add(entity: Entity){
        entities.add(entity)
    }

    public fun destroy(entity: Entity){
        entities.remove(entity)
    }

    public fun start(){
        for(entity in entities)
            entity.start()
    }

    public fun update(dt: Float){
        for(entity in entities)
            entity.update(dt)
    }

    public fun draw(){
        for(entity in entities)
            entity.draw()
    }
}