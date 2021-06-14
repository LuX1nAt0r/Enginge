package engine

import engine.gfx.Image
import java.awt.image.DataBufferInt

class Renderer {

    private var pW: Int = 0
    private var pH: Int = 0
    private lateinit var p: IntArray

    public fun Renderer(gc: GameContainer){

        pW = gc.width
        pH = gc.height
        p = (gc.window.image!!.raster.dataBuffer as DataBufferInt).data


    }

    fun clear(){
        for (i in p!!.indices){
            p!![i] =0
        }
    }

    fun setPixel(x: Int, y: Int, value: Int){
        if((x < 0 || x >= pW || y < 0 || y >= pH) || value == (0xffff00ff).toInt()){
            return
        }

        p[x + y * pW] = value
    }


    fun drawImage(image: Image, offX: Int, offY: Int){
        for (y in 0 until image.height){
            for (x in 0 until image.width){
                setPixel(x + offX, y + offY, image.p[x + y * image.width])
            }
        }
    }


}