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

    private var image: Image = Image("/test.png")
    private var image2: Image = Image("/test2.png")
    private var clip: SoundClip = SoundClip("/sounds/sound.wav")


    init {
        clip.setVolume(-20f)
        image2.alpha = true
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

        r.drawImage(image2, gc.input.mouseX, gc.input.mouseY)
        r.drawImage(image,10 ,10)


       // r.drawFilledRect(gc.input.mouseX -16, gc.input.mouseY -16, 32, 32, (0xffffccff).toInt())
    }


}

fun main(){
    val gc = GameContainer()
    gc.GameContainer(GameManager())
    gc.start()
}