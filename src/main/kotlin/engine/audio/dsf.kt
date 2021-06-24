package engine.audio

import engine.audio.dsf
import java.io.BufferedInputStream
import javax.sound.sampled.AudioInputStream
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.UnsupportedAudioFileException
import java.io.IOException
import java.io.InputStream

class dsf(path: String?) {
    init {
        val audioSrc = dsf::class.java.getResourceAsStream(path)
        val bufferdIn: InputStream = BufferedInputStream(audioSrc)
        try {
            val ais = AudioSystem.getAudioInputStream(bufferdIn)
        } catch (e: UnsupportedAudioFileException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}