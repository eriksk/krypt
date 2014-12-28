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
import org.newdawn.slick.Font
import gui.GuiMessageBox
import gui.implementations.GuiStatBox
import components.factories.MobFactory
import krypt.rand
import components.worlds.WorldComponent
import java.util.Random

class Game(title: String) : BasicGame(title) {

    val content = ContentManager("content")
    val manager = EntityManager()

    val cam = Camera(1280f, 720f)
    val uiCam = Camera(1280f, 720f)
    var font : Font? = null

    var stats : GuiStatBox? = null

    override fun init(container: GameContainer?) {
        font = content.loadFont("SDS_8x8", 8)

        val worldFactory = WorldFactory(content)
        manager.add(worldFactory.create())

        val characterFactory = CharacterFactory(content)
        val player = characterFactory.create()
        player.transform.position.set(1280/2f,720/2f)
        manager.add(player)

        val mobFactory = MobFactory(content)
        val rand = Random(System.currentTimeMillis())

        120.times{
            val cell = manager.getEntity("world").getComponent(javaClass<WorldComponent>()).world.getRandomCell(0, rand)
            manager.add(mobFactory.create((cell.col * 16f) + 8f, (cell.row * 16f) + 8f))
        }

        stats = GuiStatBox(TextureAtlas(16, content.load("gfx/DawnLike_3/GUI/GUI0")), font!!)

        cam.setPosition(1280/2f,720/2f)
        manager.start()
    }

    override fun update(container: GameContainer?, delta: Int) {
        manager.update(delta.toFloat())

        if(container?.getInput()?.isKeyPressed(Keyboard.KEY_SPACE) == true){
            manager.getEntity("world").getComponent(javaClass<WorldGeneratorComponent>()).generate(System.currentTimeMillis().toInt())
        }

        uiCam.setScale(1f)
        cam.setScale(2f)
        cam.move(manager.getEntity("player").transform.position)
        cam.update(delta.toFloat())
        uiCam.update(delta.toFloat())
    }

    override fun render(container: GameContainer?, g: Graphics?) {
        g?.setBackground(Color(100f / 255f, 149f / 255f, 237f / 255f, 1f))
        g?.clear()

        cam.lookThrough(g)
        manager.draw()

        g?.resetTransform()
        uiCam.lookThrough(g)

        stats?.draw()
    }
}