package krypt

import java.util.Random
import org.newdawn.slick.Color
import org.newdawn.slick.Image
import org.lwjgl.util.vector.Vector2f
import sprites.AtlasCell


val random = Random(System.currentTimeMillis())

/**
 * Created by erikskoglund on 2014-12-27.
 */
public fun rand(): Float{
    return random.nextFloat()
}

public fun arrayOfZeroes(size: Int): Array<Int>{
    val ary = arrayListOf<Int>()
    size.times { ary.add(0) }
    return ary.copyToArray()
}

public fun lerp(x: Float, y: Float, t: Float): Float = x + (y - x) * t

public fun renderImage(image: Image, position: Vector2f, rotation: Float, scale: Vector2f, source: AtlasCell){

    val halfWidth = source.width * scale.x / 2f
    val halfHeight = source.height * scale.y / 2f

    val color = Color.white

    image.setRotation(rotation * 180f / 6.28f)
    image.draw(
            position.x - halfWidth, position.y - halfHeight,
            position.x + halfWidth, position.y + halfHeight,
            source.x, source.y, source.x2, source.y2,
            color)
}

public fun renderImage(image: Image, position: Vector2f, rotation: Float, scale: Vector2f, color:Color){

    val halfWidth = image.getWidth() * scale.x / 2f
    val halfHeight = image.getHeight() * scale.y / 2f

    image.setRotation(rotation * 180f / 6.28f)
    image.draw(
            position.x - halfWidth, position.y - halfHeight,
            position.x + halfWidth, position.y + halfHeight,
            0f, 0f, image.getWidth().toFloat(), image.getHeight().toFloat(),
            color)
}

fun color(r: Int = 255, g: Int = 255, b:Int = 255, a:Int = 255): Color{
    return Color(r.toFloat() / 255f, g.toFloat() / 255f ,b.toFloat() / 255f,a.toFloat() / 255f)
}