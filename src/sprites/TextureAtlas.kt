package sprites

import org.newdawn.slick.opengl.Texture
import java.util.HashMap

/**
 * Created by erikskoglund on 2014-12-27.
 */
class TextureAtlas(val cellSize: Int, val texture: Texture) {

    val cellCache = hashMapOf<Int, AtlasCell>()

    val columns: Int
        get() = texture.getImageWidth() / cellSize

    val rows: Int
        get() = texture.getImageHeight() / cellSize


    public fun get(index: Int): AtlasCell {
        if (cellCache.containsKey(index))
            return cellCache.get(index)

        val col = index % columns
        val row = index / columns

        val cell = AtlasCell(this, col, row)

        cellCache.put(index, cell)
        return cell
    }

    public fun get(index: Int, columnCount:Int, rowCount:Int): AtlasCell {
        if (cellCache.containsKey(index))
            return cellCache.get(index)

        val col = index % columns
        val row = index / columns

        val cell = AtlasCell(this, col, row, columnCount, rowCount)

        cellCache.put(index, cell)
        return cell
    }
}