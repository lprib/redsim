package view

import model2.Node
import processing.core.PApplet
import processing.core.PGraphics

class NodeView(private val x: Float, private val y: Float, private val name: String, val node: Node, private val g: PGraphics): Drawable {
    override fun draw() {
        g.fill(128)
        g.rect(x, y, g.textWidth(name) + 10, 20f)
        g.fill(0)
        g.text(name, x + 5, y + 5)
    }
}