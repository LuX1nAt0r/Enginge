package engine.gfx

import java.awt.image.BufferedImage
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import javax.imageio.ImageIO

open class Image(path: String) {


    var width: Int = 0
    var height: Int = 0
    lateinit var p: IntArray
    lateinit var image: BufferedImage
    var alpha = false

    init{
        try {
            image = ImageIO.read(Image::class.java.getResourceAsStream(path))

        }
        catch (e: IOException){
            e.printStackTrace()
        }
        width = image.width
        height= image.height
        p = image.getRGB(0, 0, width, height, null, 0, width)

        image.flush()
    }

}