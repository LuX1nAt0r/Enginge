package engine.gfx

import java.awt.image.BufferedImage
import java.io.IOException
import javax.imageio.ImageIO

class Image() {

    var width: Int = 0
    var height: Int = 0
    lateinit var p: IntArray

    public fun Image(path: String){
        var image: BufferedImage? = null

        try {
            image = ImageIO.read(Image::class.java.getResourceAsStream(path))
        }
        catch (e: IOException){
            e.printStackTrace()
        }
        width = image!!.width
        height= image!!.height
        p = image.getRGB(0, 0, width, height, null, 0, width)

        image.flush()
    }

}