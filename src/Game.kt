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
import components.factories.CharacterFactory
import cameras.Camera

class Game(title: String) : BasicGame(title) {

    val content = ContentManager("content")
    val manager = EntityManager()

    // TODO: move camera to an entity?`or not?
    val cam = Camera(1280f, 720f)

    override fun init(container: GameContainer?) {

        val worldFactory = WorldFactory(content)
        manager.add(worldFactory.create())

        val characterFactory = CharacterFactory(content)
        val player = characterFactory.create()
        player.transform.position.set(1280/2f,720/2f)
        manager.add(player)

        manager.start()
    }

    override fun update(container: GameContainer?, delta: Int) {
        manager.update(delta.toFloat())

        if(container?.getInput()?.isKeyPressed(Keyboard.KEY_SPACE) == true){
            manager.getEntity("world").getComponent(javaClass<WorldGeneratorComponent>()).generate(System.currentTimeMillis().toInt())
        }

        cam.transform.scale.set(1f, 1f)

        cam.move(manager.getEntity("player").transform.position)
        cam.update(delta.toFloat())
    }

    override fun render(container: GameContainer?, g: Graphics?) {
        g?.setBackground(Color(100f / 255f, 149f / 255f, 237f / 255f, 1f))
        g?.clear()

        cam.lookThrough(g)

        manager.draw()
    }
}