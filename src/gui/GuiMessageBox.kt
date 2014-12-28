package gui

import org.newdawn.slick.Font
import sprites.TextureAtlas
import krypt.renderImage
import org.lwjgl.util.vector.Vector2f
import colors.ColorScheme

/**
 * Created by Erik on 2014-12-28.
 */
class GuiMessageBox(atlas: TextureAtlas, font: Font, val message:String) : GuiElement(atlas, font){
    val boxArea = atlas.get(221, 3, 3)
    val text = Text(message, TextAlign.CENTER, font, ColorScheme.red, true)

    override fun update(dt: Float){

    }

    override fun draw(){
        boxArea.draw(Vector2f(0f, 0f), 0f, Vector2f(4f, 4f))
        text.draw(Vector2f(0f, 0f))
    }
}