package game


import engine.AbstractGame
import engine.GameContainer
import engine.Renderer
import engine.gfx.Image
import java.awt.event.KeyEvent
import kotlin.jvm.JvmStatic
import game.GameManagerJav

class GameManagerJav : AbstractGame() {
    private val image: Image
    override fun update(gc: GameContainer, dt: Float) {
        if (gc.input.isKeyDown(KeyEvent.VK_A)) {
            println("A")
        }
    }

    override fun render(gc: GameContainer, r: Renderer) {}

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val gc = GameContainer()
            gc.GameContainer(GameManagerJav())
            gc.start()
        }
    }

    init {
        image = Image()
        image.Image("resources/test.png")
    }
}