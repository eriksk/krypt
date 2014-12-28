package gui

import sprites.TextureAtlas
import org.newdawn.slick.Font

/**
 * Created by Erik on 2014-12-28.
 */
open class GuiElement(val atlas: TextureAtlas, val font: Font){
    public open fun update(dt: Float){}
    public open fun draw(){}
}