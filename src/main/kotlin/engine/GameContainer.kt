package engine

import java.awt.SystemColor.window
import java.lang.Runnable
import java.lang.InterruptedException
import kotlin.jvm.JvmStatic

class GameContainer : Runnable {
    private var thread: Thread? = null
    lateinit var window: Window
    lateinit var renderer: Renderer


    private var running = false
    private val UPDATE_CAP = 1.0 / 60.0

    var width = 320
    var height = 240
    var scale : Float = 3f
    var title = "LukasEngine v1.0"


    fun start() {
        window = Window()
        window.Window(this)

        renderer = Renderer()
        renderer.Renderer(this)

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
        var fps: Int


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



                //TODO: Update Game
                if (frametime >= 1.0){
                    frametime = 0.0
                    fps = frames
                    frames = 0
                    println("FPS: $fps")
                }

            }
            if (render) {
                //TODO: Render Game
                    renderer.clear()



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

public fun main(){
    val gc = GameContainer()
    gc.start()
}