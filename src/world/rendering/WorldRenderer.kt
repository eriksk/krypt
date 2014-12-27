package world.rendering

import sprites.TextureAtlas
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import world.generation.World
import org.newdawn.slick.Color

/**
 * Created by erikskoglund on 2014-12-27.
 */
class WorldRenderer(val atlas: TextureAtlas){

    val image = Image(atlas.texture)
    val translations = hashMapOf<Int, Int>()

    public fun load(){
        image.setFilter(Image.FILTER_NEAREST)

        translations.clear()

        translations.put(0 ,3)
        translations.put(1, 1)
        translations.put(2, 20)
        translations.put(3, 2)
        translations.put(4, 1)
        translations.put(5, 1)
        translations.put(6, 42)
        translations.put(7, 2)
        translations.put(8, 20)
        translations.put(9, 0)
        translations.put(10, 20)
        translations.put(11, 4)
        translations.put(12, 40)
        translations.put(13, 0)
        translations.put(14, 21)
        translations.put(15, 21)
        translations.put(16, 9)
    }

    public fun render(world: World){
        val startRow = 6

        image.startUse()

        world.width.indices.forEach { col ->
            world.height.indices.forEach { row ->
                val cell = world.get(col, row)
                val atlasCell = atlas.get(if(cell == 16) 9 else translations.get(cell) + (startRow * atlas.columns))

                image.drawEmbedded(
                        col * atlas.cellSize.toFloat(), row * atlas.cellSize.toFloat(),
                        (col * atlas.cellSize + atlas.cellSize).toFloat(), (row * atlas.cellSize + atlas.cellSize).toFloat(),
                        atlasCell.x.toFloat(), atlasCell.y.toFloat(), atlasCell.x2.toFloat(), atlasCell.y2.toFloat())
            }
        }

        image.endUse()
    }

    public fun renderDebug(world: World, graphics: Graphics?){
        graphics?.setColor(Color(1f, 1f, 0f, 0.8f))
        world.width.indices.forEach { col ->
            world.height.indices.forEach { row ->
                val cell = world.get(col, row)
                graphics?.drawString("$cell", col * atlas.cellSize.toFloat(), row * atlas.cellSize.toFloat())
            }
        }
    }
}