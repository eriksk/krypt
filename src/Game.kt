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

class Game(title: String) : BasicGame(title) {

    val content = ContentManager("content")
    val manager = EntityManager()

    override fun init(container: GameContainer?) {

        manager.add(createPaladin())
        manager.start()
    }

    fun createPaladin() : Entity{
        val paladin = Entity("Paladin")

        var animations = hashMapOf<String, FrameStepAnimation>()
        animations.put("walk_south", animations.FrameStepAnimation(array(0, 1, 2, 3), 100f))
        animations.put("walk_west", animations.FrameStepAnimation(array(4, 5, 6, 7), 100f))
        animations.put("walk_east", animations.FrameStepAnimation(array(8, 9, 10, 11), 100f))
        animations.put("walk_north", animations.FrameStepAnimation(array(12, 13, 14, 15), 100f))

        paladin.addComponent(TextureRenderSystem(paladin, content.load("gfx/paladin")))
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
    }
}