package view

import model2.Connection
import processing.core.PGraphics

class ConnectionView(var x: Float, var y: Float, val g: PGraphics, val connection: Connection) : Drawable {
    override fun draw() {
        g.fill(
            if (connection.state) {
                255
            } else {
                0
            }
        )

        g.ellipse(x, y, 10f, 10f)
    }
}