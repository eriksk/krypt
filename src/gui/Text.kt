package gui

import org.newdawn.slick.Font
import org.lwjgl.util.vector.Vector2f
import org.newdawn.slick.Color

/**
 * Created by Erik on 2014-12-28.
 */
class Text(var content: String, var align: TextAlign, val font: Font, val color: Color = Color(1f, 1f, 1f, 1f), val wrap: Boolean = true){

    val shadowColor = krypt.color(0, 0, 0, 160)
    val lines = if(wrap) content.split('\n') else array(content)

    fun getOffset(text: String): Float {
        if (align == TextAlign.RIGHT) {
            return font.getWidth(text).toFloat()
        } else if (align == TextAlign.CENTER) {
            return font.getWidth(text) / 2f
        }
        return 0f
    }

    public fun draw(position: Vector2f){
        val lineHeight = font.getLineHeight()

        for(line in lines.indices){
            val text = lines[line]
            val offset = getOffset(text)

            font.drawString(position.x - offset +1f, position.y +1f + (line * lineHeight), text, shadowColor)
            font.drawString(position.x - offset, position.y + (line * lineHeight), text, color)
        }
    }
}