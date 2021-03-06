package skoggy.ecs

/**
 * Created by erikskoglund on 2014-12-25.
 */
class EntityManager{
    val entities = arrayListOf<Entity>()

    public fun getEntity(name: String):Entity{
        for(entity in entities){
            if(entity.name.equals(name))
                return entity
        }
        throw IllegalArgumentException("Entity with name '$name' does not exist")
    }

    fun clear() {
        entities.clear()
    }

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