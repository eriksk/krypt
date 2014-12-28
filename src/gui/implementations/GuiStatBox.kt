package gui.implementations

import gui.GuiElement
import sprites.TextureAtlas
import org.newdawn.slick.Font
import gui.Text
import gui.TextAlign
import colors.ColorScheme
import org.lwjgl.util.vector.Vector2f

/**
 * Created by Erik on 2014-12-28.
 */
class GuiStatBox(atlas: TextureAtlas, font: Font) : GuiElement(atlas, font){
    val boxArea = atlas.get(173, 3, 3)
    val text = Text("Player 1", TextAlign.CENTER, font, ColorScheme.green, true)

    override fun update(dt: Float){

    }

    override fun draw(){
        val position = Vector2f(-500f, -246f)
        boxArea.draw(position, 0f, Vector2f(4f, 4f))
        text.draw(Vector2f(position.x, position.y - 74f))
    }
}