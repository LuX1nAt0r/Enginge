package engine.gfx

class Font(path: String) {

    var fontImage: Image = Image(path)
    var offsets: IntArray = IntArray(59)
    var widths: IntArray = IntArray(59)

    companion object {
        val STANDARD = Font("standard.png")
    }

    init {
        var unicode: Int = 0
        for (i in 0 until fontImage.width){
            if (fontImage.p[i] == (0xFF0000FF).toInt()){
                offsets[unicode] = i
            }

            if (fontImage.p[i]== (0xffffff00).toInt()){
                widths[unicode] = i - offsets[unicode]
                unicode++
            }
        }
    }

}