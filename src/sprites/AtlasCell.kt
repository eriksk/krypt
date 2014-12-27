package sprites

/**
 * Created by erikskoglund on 2014-12-27.
 */
data class AtlasCell(val atlas : TextureAtlas, val col: Int, val row:Int){
    val x = col * atlas.cellSize
    val y = row * atlas.cellSize
    val x2 = (col * atlas.cellSize) + atlas.cellSize
    val y2 = (row * atlas.cellSize) + atlas.cellSize
}