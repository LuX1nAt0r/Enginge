package game

import engine.AbstractGame
import engine.GameContainer
import engine.Input
import engine.Renderer
import engine.gfx.Image
import engine.gfx.ImageTile
import java.awt.event.KeyEvent

class GameManager: AbstractGame() {

    private var image: ImageTile = ImageTile("/test.png", 16, 16)

    /*init {
        image.
    }*/

    override fun update(gc: GameContainer, dt: Float) {

        if (gc.input.isKeyDown(KeyEvent.VK_A)){
            println("A was pressed")
        }
        temp += dt * 5

        if(temp >3){
            temp = 0f
        }

    }

    var temp: Float = 0F




    override fun render(gc: GameContainer, r: Renderer) {

        r.drawImageTile(image, gc.input.mouseX - 8, gc.input.mouseY -16, temp.toInt(),0)
    }


}

fun main(){
    val gc = GameContainer()
    gc.GameContainer(GameManager())
    gc.start()
}