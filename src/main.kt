import com.sun.xml.internal.bind.v2.schemagen.xmlschema.SimpleContent
import processing.core.PApplet
import model.*

fun main() {
//    PApplet.main("Main")
    val w1 = Wire()
    val w2 = Wire()
    val g = Wire()
    val gate = GatedConnection()
    gate.setInput("w1", w1)
    gate.setInput("w2", w2)
    gate.setInput("gate", g)
    w1.connectedWires.add(gate)
    w2.connectedWires.add(gate)

    val items: List<SimItem> = listOf(w1, w2, g, gate)

    //TODO debug this shit
    while (true) {
        val a = readLine()
        if (a == "1") {
            w1.queueValue(true)
        } else {
            w1.queueValue(false)
        }

        val b = readLine()
        if (a == "1") {
            g.queueValue(true)
        } else {
            g.queueValue(false)
        }


        items.forEach { it.clock() }
        items.forEach { it.resolveWires() }
        println(w2.state)
    }
}

class Main : PApplet() {
    override fun settings() {
        size(400, 400);
    }

    override fun setup() {

    }

    override fun draw() {
        ellipse(mouseX.toFloat(), mouseY.toFloat(), 10f, 10f);
    }
}