package engine

import java.awt.event.*

class Input: KeyListener , MouseListener, MouseMotionListener, MouseWheelListener {

    private var gc = GameContainer()

    private final val NUM_KEYS = 256
    private var keys :BooleanArray = BooleanArray(NUM_KEYS)
    private var keysLast :BooleanArray = BooleanArray(NUM_KEYS)


    private final val NUM_BUTTONS = 5
    private var buttons :BooleanArray = BooleanArray(NUM_BUTTONS)
    private var buttonsLast :BooleanArray = BooleanArray(NUM_BUTTONS)

    var mouseX: Int = 0
    var mouseY: Int = 0

    var scroll: Int = 0


    fun Input(gc: GameContainer){

        this.gc = gc
        mouseX =0
        mouseY =0
        scroll =0

        gc.window.canvas.addKeyListener(this)
        gc.window.canvas.addMouseMotionListener(this)
        gc.window.canvas.addMouseListener(this)
        gc.window.canvas.addMouseWheelListener(this)
    }

    fun update(){

        scroll =0

        for (i in 0 until NUM_KEYS){
            keysLast[i] = keys[i]
        }

        for (i in 0 until NUM_BUTTONS){
            buttonsLast[i] = buttons[i]
        }
    }

    fun isKey(keyCode: Int):Boolean {
        return keys[keyCode]
    }

    fun isKeyUp(keyCode: Int):Boolean {
        return !keys[keyCode] && keysLast[keyCode]
    }

    fun isKeyDown(keyCode: Int):Boolean {
        return keys[keyCode] && !keysLast[keyCode]
    }


    fun isButton(button: Int):Boolean {
        return buttons[button]
    }

    fun isButtonUp(button: Int):Boolean {
        return !buttons[button] && buttonsLast[button]
    }

    fun isButtonDown(button: Int):Boolean {
        return buttons[button] && !buttonsLast[button]
    }


    override fun keyTyped(e: KeyEvent?) {

    }

    override fun keyPressed(e: KeyEvent) {
        keys[e.keyCode] = true
    }

    override fun keyReleased(e: KeyEvent) {
        keys[e.keyCode] = false
    }

    override fun mouseClicked(e: MouseEvent?) {

    }

    override fun mousePressed(e: MouseEvent) {
        buttons[e.button] = true
    }

    override fun mouseReleased(e: MouseEvent) {
        buttons[e.button] = false
    }

    override fun mouseEntered(e: MouseEvent?) {

    }

    override fun mouseExited(e: MouseEvent?) {

    }

    override fun mouseDragged(e: MouseEvent) {
        mouseX = (e.x / gc.scale).toInt()
        mouseY = (e.y / gc.scale).toInt()
    }

    override fun mouseMoved(e: MouseEvent) {
        mouseX = (e.x / gc.scale).toInt()
        mouseY = (e.y / gc.scale).toInt()
    }

    override fun mouseWheelMoved(e: MouseWheelEvent) {
        scroll = e.wheelRotation
    }

}