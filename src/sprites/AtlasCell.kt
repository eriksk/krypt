package sprites

import org.newdawn.slick.Image
import krypt.renderImage
import org.lwjgl.util.vector.Vector2f

/**
 * Created by erikskoglund on 2014-12-27.
 */
data class AtlasCell(val atlas : TextureAtlas, val col: Int, val row:Int, columns:Int = 1, rows: Int = 1){
    var image: Image? = null

    val x = (col * atlas.cellSize).toFloat()
    val y = (row * atlas.cellSize).toFloat()
    val x2 = ((col * atlas.cellSize) + atlas.cellSize * columns).toFloat()
    val y2 = ((row * atlas.cellSize) + atlas.cellSize * rows).toFloat()
    val width = (atlas.cellSize * columns).toFloat()
    val height = (atlas.cellSize * rows).toFloat()

    public fun draw(position: Vector2f, rotation: Float, scale: Vector2f){
        if(image == null){
            image = Image(atlas.texture)
            image?.setFilter(Image.FILTER_NEAREST)
        }

        renderImage(image!!, position, rotation, scale, this)
    }
}