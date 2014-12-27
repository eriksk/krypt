package content

import org.newdawn.slick.opengl.Texture
import org.newdawn.slick.util.ResourceLoader
import org.newdawn.slick.opengl.TextureLoader

/**
 * Created by erikskoglund on 2014-12-25.
 */
class ContentManager(val contentRoot: String){

    val cache = hashMapOf<String, Texture>()

    public fun load(name : String): Texture {
        if(cache.containsKey(name))
            return cache.get(name)
        val texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("$contentRoot/$name.png"))
        cache.put(name, texture)
        return texture
    }
}