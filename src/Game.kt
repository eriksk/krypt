import org.newdawn.slick.BasicGame
import org.newdawn.slick.GameContainer
import org.newdawn.slick.Graphics
import org.newdawn.slick.opengl.Texture
import org.newdawn.slick.opengl.TextureLoader
import java.io.File
import org.newdawn.slick.util.ResourceLoader
import org.newdawn.slick.Image
import skoggy.ecs.EntityManager
import skoggy.ecs.Entity
import components.rendering.TextureRenderSystem
import components.animation.AnimationComponent
import animations.FrameStepAnimation
import components.input.AnimationInputControllerComponent
import content.ContentManager
import world.generation.World
import world.tiling.AutoTiler
import world.generation.WorldGenerator
import world.rendering.WorldRenderer
import sprites.TextureAtlas
import org.lwjgl.input.Keyboard
import org.newdawn.slick.Color
import kotlin.concurrent.thread
import components.factories.WorldFactory
import components.worlds.WorldGeneratorComponent

class Game(title: String) : BasicGame(title) {

    val content = ContentManager("content")
    val manager = EntityManager()


    override fun init(container: GameContainer?) {

        val worldFactory = WorldFactory(content)
        manager.add(worldFactory.create())

        manager.start()
    }

    override fun update(container: GameContainer?, delta: Int) {
        manager.update(delta.toFloat())

        if(container?.getInput()?.isKeyPressed(Keyboard.KEY_SPACE) == true){
            manager.getEntity("world").getComponent(javaClass<WorldGeneratorComponent>()).generate(System.currentTimeMillis().toInt())
        }
    }

    override fun render(container: GameContainer?, g: Graphics?) {
        g?.setBackground(Color(100f / 255f, 149f / 255f, 237f / 255f, 1f))
        g?.clear()

        manager.draw()
    }
}