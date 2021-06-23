package engine

import engine.gfx.Font
import engine.gfx.Image
import engine.gfx.ImageTile
import java.awt.image.DataBufferInt

class Renderer {

    private var pW: Int = 0
    private var pH: Int = 0
    private lateinit var p: IntArray

   private var font = Font.STANDARD

    //private var font: Font = Font("/fonts/standard.png")





    fun Renderer(gc: GameContainer){

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

    fun drawText(text: String, offX: Int, offY: Int, color:Int){

        text.toUpperCase()
        var offset = 0

        for (i in text.indices){
            var unicode: Int = text.codePointAt(i) -32


            for (y in 0 until font.fontImage.height){
                for (x in 0 until font.widths[unicode]){

                    if (font.fontImage.p[(x + font.offsets[unicode]) + y * font.fontImage.width] == (0xffffffff).toInt()){
                        setPixel(x + offX,y + offY,color)
                    }
                }
            }

            offset += font.widths[unicode]

        }

    }




    fun drawImage(image: Image, offX: Int, offY: Int){

        //Don't Render code
        if(offX < -image.width) return
        if(offY < -image.height) return
        if (offX >= pW) return
        if (offY >= pH) return

        var newX = 0
        var newY = 0
        var newWidth = image.width
        var newHeight = image.height

//Clipping code
        if(offX < 0){
            newX -= offX }
        if (offY < 0){
            newY -= offY }
        if(newWidth + offX >= pW){
            newWidth -= (newWidth + offX - pW) }
        if(newHeight + offY >= pH){
            newHeight -= (newHeight + offY - pH) }





        for (y in newY until newHeight){
            for (x in newX until newWidth){
                setPixel(x + offX, y + offY, image.p[x + y * image.width])
            }
        }
    }

    fun drawImageTile(image:ImageTile, offX: Int, offY: Int, tileX: Int, tileY: Int){

//Don't Render code
        if(offX < -image.tileW) return
        if(offY < -image.tileH) return
        if (offX >= pW) return
        if (offY >= pH) return

        var newX = 0
        var newY = 0
        var newWidth = image.tileW
        var newHeight = image.tileH

//Clipping code
        if(offX < 0){
            newX -= offX }
        if (offY < 0){
            newY -= offY }
        if(newWidth + offX >= pW){
            newWidth -= (newWidth + offX - pW) }
        if(newHeight + offY >= pH){
            newHeight -= (newHeight + offY - pH) }

        for (y in newY until newHeight){
            for (x in newX until newWidth){
                setPixel(
                    x + offX, y + offY, image.p[(x +tileX * image.tileW) + (y +tileY * image.tileH) * image.width]
                )
            }
        }
    }


}