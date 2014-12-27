package sprites

import org.newdawn.slick.opengl.Texture
import java.util.HashMap

/**
 * Created by erikskoglund on 2014-12-27.
 */
class TextureAtlas(val cellSize: Int, val texture: Texture){

    val cellCache = hashMapOf<Int, AtlasCell>()

    val columns : Int
        get() = texture.getTextureWidth() / cellSize

    val rows : Int
        get() = texture.getTextureHeight() / cellSize


    public fun get(index: Int): AtlasCell {
        if (cellCache.containsKey(index))
            return cellCache.get(index)

        val cell = AtlasCell(this, index % columns, index / rows)
        cellCache.put(index, cell)
        return cell
    }
}