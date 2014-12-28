package content

/**
 * Created by Erik on 2014-12-28.
 */
class Cache<T>{
    val cache = hashMapOf<String, T>()

    public fun clear(){
        cache.clear()
    }

    public fun put(name: String, value: T){
        cache.put(name, value)
    }

    public fun get(name: String): T{
        return cache.get(name)
    }

    public fun contains(name: String): Boolean{
        return cache.containsKey(name)
    }
}