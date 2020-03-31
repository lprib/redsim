package view

import model2.SimulationManager
import model2.Wire
import processing.core.PApplet

class Main: PApplet() {
    val manager = SimulationManager()
    lateinit var n: NodeView

    override fun settings() {
        size(500, 500)
    }

    override fun setup() {
        n = NodeView(40f, 40f, "AND", Wire(manager), graphics)
        textSize(10f)
        textAlign(LEFT, TOP)
    }

    override fun draw() {
        ellipse(mouseX.toFloat(), mouseY.toFloat(), 10f, 10f)
        n.draw()
    }

    override fun keyPressed() {
    }
}