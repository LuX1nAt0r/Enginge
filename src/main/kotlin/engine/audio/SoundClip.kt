package engine.audio

import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import javax.sound.sampled.*
import javax.sound.sampled.AudioSystem.getAudioInputStream

class SoundClip(path: String) {

    private var clip: Clip? = null
    lateinit var gainControl: FloatControl


    init {


        try {
            val audioSrc: InputStream = SoundClip::class.java.getResourceAsStream(path)
            val bufferedIn: InputStream = BufferedInputStream(audioSrc)
            var ais: AudioInputStream = getAudioInputStream(bufferedIn)
            var baseFormat: AudioFormat = ais.format
            var decodeFormat: AudioFormat = AudioFormat(
                                                        AudioFormat.Encoding.PCM_SIGNED,
                                                        baseFormat.sampleRate,
                                            16,
                                                        baseFormat.channels,
                                                baseFormat.channels *2 ,
                                                baseFormat.sampleRate,
                                                false
                                         )
            var dais: AudioInputStream = AudioSystem.getAudioInputStream(decodeFormat, ais)

            clip = AudioSystem.getClip()
            clip!!.open(dais)

            gainControl = clip!!.getControl(FloatControl.Type.MASTER_GAIN) as FloatControl


        } catch (e: UnsupportedAudioFileException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun play(){
        if(clip == null){
            return
        }

            stop()
            clip!!.framePosition = 0
            while (!clip!!.isRunning){
                clip!!.start()
            }

    }

    fun stop(){
        if(clip!!.isRunning){
            clip!!.stop()
        }
    }

    fun close(){
        stop()
        clip!!.drain()
        clip!!.close()
    }

    fun loop(){
        clip!!.loop(Clip.LOOP_CONTINUOUSLY)
        play()
    }

    fun setVolume(value: Float){
        gainControl.value = value

    }

    fun isRunning(): Boolean {
        return clip!!.isRunning
    }



}