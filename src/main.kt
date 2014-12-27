import org.newdawn.slick.Game
import org.newdawn.slick.AppGameContainer

/**
 * Created by erikskoglund on 2014-12-25.
 */

fun main(args: Array<String>) {
    val game = Game("Hello")

    val container = AppGameContainer(game, 1280, 720, false)
    container.start()
}