package skoggy.ecs

/**
 * Created by erikskoglund on 2014-12-25.
 */
public class Entity(val name: String){

    private val components = arrayListOf<Component>()

    val transform = Transform()

    public fun addComponent(component : Component){
        components.add(component)
    }

    fun start(){
        onStart()
        for(component in components)
            component.onStart()
    }

    public open fun onStart(){}
    public open fun onUpdate(dt : Float){}

    public fun getComponent<T>(clazz : Class<T>): T{
        for(component in components){
            if (component.javaClass == clazz)
                return component as T
        }
        throw Exception("Component does not exist")
    }

    fun update(dt : Float){
        onUpdate(dt)

        for(component in components){
            when (component) {
                is Updateable -> component.update(dt)
            }
        }
    }

    fun draw(){
        for(component in components){
            when (component) {
                is Renderable -> component.render()
            }
        }
    }
}