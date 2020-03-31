package model2

import java.lang.IllegalArgumentException

abstract class Node(val manager: SimulationManager): Clockable {
    abstract val connections: List<Connection>

    val onConnectionsChange = Event<Unit>()

    operator fun get(name: String): Connection =
        connections.find { it.name == name } ?: throw IllegalArgumentException("no connection named `$name`")
}