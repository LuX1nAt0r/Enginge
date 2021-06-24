package engine

import java.awt.SystemColor.window
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import java.lang.Runnable
import java.lang.InterruptedException
import kotlin.jvm.JvmStatic

class GameContainer : Runnable {
    private var thread: Thread? = null
    lateinit var window: Window
    lateinit var renderer: Renderer
    lateinit var input: Input
    lateinit var game: AbstractGame


    private var running = false
    private val UPDATE_CAP = 1.0 / 60.0

    var width = 320
    var height = 240
    var scale : Float = 3f
    var title = "LukasEngine v1.0"

    fun GameContainer(game: AbstractGame){
        this.game = game
    }


    fun start() {
        window = Window()
        window.Window(this)

        renderer = Renderer()
        renderer.Renderer(this)

        input = Input()
        input.Input(this)


        thread = Thread(this)
        thread!!.run()
    }


    fun stop() {}


    override fun run() {
        running = true
        var render = false
        var firstTime = 0.0
        var lastTime = System.nanoTime() / 1000000000.0
        var passedTime: Double
        var unprocessedTime = 0.0

        var frametime:Double = 0.0
        var frames = 0
        var fps: Int = 0


        while (running) {

            render = false

            firstTime = System.nanoTime() / 1000000000.0
            passedTime = firstTime - lastTime
            lastTime = firstTime
            unprocessedTime += passedTime
            frametime += passedTime


            while (unprocessedTime >= UPDATE_CAP) {
                unprocessedTime -= UPDATE_CAP
                render = true




                game.update(this, UPDATE_CAP.toFloat())



                input.update()





                if (frametime >= 1.0){
                    frametime = 0.0
                    fps = frames
                    frames = 0
                }

            }
            if (render) {


                    renderer.clear()

                    game.render(this, renderer)
                    renderer.drawText("FPS: $fps", 0,0,(0xff00ffff).toInt())

                    window.update()
                    frames++


            } else {
                try {
                    Thread.sleep(1)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
        dispose()
    }

    private fun dispose() {}


}

