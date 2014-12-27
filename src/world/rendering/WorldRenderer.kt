package world.rendering

import sprites.TextureAtlas
import org.newdawn.slick.Graphics
import org.newdawn.slick.Image
import world.generation.World

/**
 * Created by erikskoglund on 2014-12-27.
 */
class WorldRenderer(val atlas: TextureAtlas){

    val image = Image(atlas.texture)

    public fun render(world: World){
        image.startUse()

        world.width.indices.forEach { col ->
            world.height.indices.forEach { row ->
                val cell = world.get(col, row)
                val atlasCell = atlas.get(cell)
                image.draw(
                        col * atlas.cellSize.toFloat(), row * atlas.cellSize.toFloat(),
                        atlasCell.x.toFloat(), atlasCell.y.toFloat(), atlasCell.x2.toFloat(), atlasCell.y2.toFloat())
            }
        }

        image.endUse()
    }
}