package skoggy.ecs

/**
 * Created by erikskoglund on 2014-12-25.
 */
public open class Component(val entity: Entity){
    val transform = entity.transform
        get

    open fun onStart(){
    }

    protected fun getComponent<T>(clazz: Class<T>) : T{
        return entity.getComponent<T>(clazz)
    }
}