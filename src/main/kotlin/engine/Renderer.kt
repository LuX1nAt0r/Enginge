package engine

import engine.gfx.Font
import engine.gfx.Image
import engine.gfx.ImageRequest
import engine.gfx.ImageTile

import java.awt.image.DataBufferInt
import java.util.*
import kotlin.collections.ArrayList

class Renderer(gc: GameContainer) {

    private var font = Font.STANDARD
    private var imageRequest: ArrayList<ImageRequest> = ArrayList<ImageRequest>()

    private var pW: Int = 0
    private var pH: Int = 0
    private var p: IntArray

    private var zBuffer: IntArray
    private var zDepth = 0
    private var processing = false


    init{
        pW = gc.width
        pH = gc.height
        p = (gc.window.image!!.raster.dataBuffer as DataBufferInt).data
        zBuffer = IntArray(p.size)

    }

    fun clear(){
        for (i in p.indices){
            p[i] =0
            zBuffer[i] = 0
        }
    }

    fun process(){
        processing = true



        for (i in 0 until imageRequest.size){
            var ir: ImageRequest = imageRequest[i]
            zDepth = ir.zDepth
            ir.image.alpha = false
            drawImage(ir.image, ir.offX, ir.offY)
        }

        imageRequest.clear()
        processing = false
    }



    private fun setPixel(x: Int, y: Int, value: Int){

        val alpha = value shr 24 and 0xff




        if((x < 0 || x >= pW || y < 0 || y >= pH) || alpha == 0){
            return
        }

        var index = x + y * pW

        if (zBuffer[index] > zDepth){
            return
        }

        zBuffer[index] = zDepth

        if(alpha == 255){
            p[index] = value
        }
        else{
            val pixelColor= p[index]

            val tempAlpha: Float = (alpha.toFloat() / 255f)
            val oneMinusAlpha = 1f - tempAlpha

            val oldR = pixelColor shr 16 and 0xff
            val oldG = pixelColor shr 8 and 0xff
            val oldB = pixelColor and 0xff

            val tempR = value shr 16 and 0xff
            val tempG = value shr 8 and 0xff
            val tempB = value and 0xff

            val newR = ((tempR * tempAlpha) + (oneMinusAlpha * oldR)).toInt()
            val newG = ((tempG * tempAlpha) + (oneMinusAlpha * oldG)).toInt()
            val newB = ((tempB * tempAlpha) + (oneMinusAlpha * oldB)).toInt()

            p[index] = (255 shl 24) or (newR shl 16) or (newG shl 8) or (newB shl 0)

        }

    }

    fun drawText(text: String, offX: Int, offY: Int, color:Int){
        text.toUpperCase()
        var offset = 0

        for (i in text.indices){
            val unicode: Int = text.codePointAt(i) -32

            for (y in 0 until font.fontImage.height){
                for (x in 0 until font.widths[unicode]){

                    if (font.fontImage.p[(x + font.offsets[unicode]) + y * font.fontImage.width] == (0xffffffff).toInt()){
                        setPixel(x + offX + offset,y + offY,color)
                    }
                }
            }

            offset += font.widths[unicode]

        }

    }




    fun drawImage(image: Image, offX: Int, offY: Int){

        if (image.alpha && !processing){
            imageRequest.add(ImageRequest(image, zDepth, offX, offY))
            return
        }

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


    fun drawRect(offX: Int, offY: Int, width: Int, height: Int, color:Int){






        for (y in 0 until height +1){
            setPixel(offX, y + offY, color)
            setPixel(offX + width, y + offY, color)

        }
        for (x in 0 until width +1) {
            setPixel(x + offX, offY, color)
            setPixel(x + offX, offY + height, color)
        }
    }

    fun drawFilledRect(offX: Int, offY: Int, width: Int, height: Int, color:Int){


        //Don't Render code
        if(offX < -width) return
        if(offY < -height) return
        if (offX >= pW) return
        if (offY >= pH) return

        var newX = 0
        var newY = 0
        var newWidth = width
        var newHeight = height

//Clipping code
        if(offX < 0){
            newX -= offX }
        if (offY < 0){
            newY -= offY }
        if(newWidth + offX >= pW){
            newWidth -= (newWidth + offX - pW) }
        if(newHeight + offY >= pH){
            newHeight -= (newHeight + offY - pH) }





        for (y in newY until newHeight +1){
            for (x in newX until newWidth +1) {
                setPixel(x+ offX, y + offY, color)
            }
        }

    }

}