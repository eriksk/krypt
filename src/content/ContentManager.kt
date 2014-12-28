package content

import org.newdawn.slick.opengl.Texture
import org.newdawn.slick.util.ResourceLoader
import org.newdawn.slick.opengl.TextureLoader
import org.newdawn.slick.Font
import java.awt
import org.newdawn.slick.TrueTypeFont

/**
 * Created by erikskoglund on 2014-12-25.
 */
class ContentManager(val contentRoot: String){

    val textureCache = Cache<Texture>()

    public fun load(name : String): Texture {
        if(textureCache.contains(name))
            return textureCache.get(name)
        val texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("$contentRoot/$name.png"))
        textureCache.put(name, texture)
        return texture
    }

    public fun loadFont(name: String, size: Int): Font{
        val awtFont = awt.Font(name, awt.Font.PLAIN, size)
        val font = TrueTypeFont(awtFont, false)
        return font
    }
}