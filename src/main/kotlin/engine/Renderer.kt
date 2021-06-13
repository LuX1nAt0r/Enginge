package engine

import java.awt.image.DataBufferInt

class Renderer {

    private var pW: Int? =null
    private var pH: Int? =null
    private var p: IntArray? =null

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


}