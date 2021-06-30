package game

import engine.AbstractGame
import engine.GameContainer
import engine.Input
import engine.Renderer
import engine.audio.SoundClip
import engine.gfx.Image
import engine.gfx.ImageTile
import java.awt.event.KeyEvent

class GameManager: AbstractGame() {

    private var image: ImageTile = ImageTile("/test.png", 16, 16)
    private var clip: SoundClip = SoundClip("/sounds/sound.wav")


    init {
        clip.setVolume(-20f)
    }

    override fun update(gc: GameContainer, dt: Float) {

        if (gc.input.isKeyDown(KeyEvent.VK_A)){
            clip.play()
        }
        temp += dt * 5

        if(temp >3){
            temp = 0f
        }

    }

    var temp: Float = 0F




    override fun render(gc: GameContainer, r: Renderer) {

        r.drawImageTile(image, gc.input.mouseX - 8, gc.input.mouseY -16, temp.toInt(),0)
       // r.drawFilledRect(gc.input.mouseX -16, gc.input.mouseY -16, 32, 32, (0xffffccff).toInt())
    }


}

fun main(){
    val gc = GameContainer()
    gc.GameContainer(GameManager())
    gc.start()
}