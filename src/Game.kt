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

class Game(title: String) : BasicGame(title) {

    val content = ContentManager("content")
    val manager = EntityManager()

    val world = World(128, 128, AutoTiler(0))
    var worldRenderer: WorldRenderer? = null

    override fun init(container: GameContainer?) {

        worldRenderer = WorldRenderer(TextureAtlas(16, content.load("gfx/DawnLike_3/Objects/Wall")))

        //manager.add(createPaladin())
        val generator = WorldGenerator(world)
        generator.generate()

        manager.start()
    }

    fun createPaladin() : Entity{
        val paladin = Entity("Paladin")

        var animations = hashMapOf<String, FrameStepAnimation>()
        animations.put("walk_south", FrameStepAnimation(array(0, 1, 2, 3), 100f))
        animations.put("walk_west", FrameStepAnimation(array(4, 5, 6, 7), 100f))
        animations.put("walk_east", FrameStepAnimation(array(8, 9, 10, 11), 100f))
        animations.put("walk_north", FrameStepAnimation(array(12, 13, 14, 15), 100f))

        paladin.addComponent(TextureRenderSystem(paladin, content.load("gfx/DawnLike_3/Commissions/Paladin")))
        paladin.addComponent(AnimationComponent(paladin, animations))
        paladin.addComponent(AnimationInputControllerComponent(paladin))

        paladin.transform.position.set(1280/2f, 720/2f)

        return paladin
    }

    override fun update(container: GameContainer?, delta: Int) {
        manager.update(delta.toFloat())
    }

    override fun render(container: GameContainer?, g: Graphics?) {
        manager.draw()
        worldRenderer?.render(world)
    }
}