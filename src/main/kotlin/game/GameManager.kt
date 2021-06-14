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
        image.Image("resources/test.png")
    }


    /*fun GameManager(){
        image = Image()
        image.Image("resources/test.png")
    }*/



    override fun update(gc: GameContainer, dt: Float) {

        if (gc.input.isKeyDown(KeyEvent.VK_A)){
            println("A was pressed")
        }

    }

    override fun render(gc: GameContainer, r: Renderer) {

    }


}

fun main(){
    val gc = GameContainer()
    gc.GameContainer(GameManager())
    gc.start()
}