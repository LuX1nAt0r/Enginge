package game

import engine.AbstractGame
import engine.GameContainer
import engine.Input
import engine.Renderer
import engine.gfx.Image
import java.awt.event.KeyEvent

class GameManager: AbstractGame() {

    private var image: Image = Image()

    init {
        image.Image("/test.png")
    }

    override fun update(gc: GameContainer, dt: Float) {

        if (gc.input.isKeyDown(KeyEvent.VK_A)){
            println("A was pressed")
        }

    }

    override fun render(gc: GameContainer, r: Renderer) {

        r.drawImage(image, gc.input.mouseX, gc.input.mouseY)
    }


}

fun main(){
    val gc = GameContainer()
    gc.GameContainer(GameManager())
    gc.start()
}