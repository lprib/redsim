package model2

class Wire(manager: SimulationManager) : Node(manager) {
    private val _connections = mutableListOf<Connection>()
    override val connections: List<Connection> = _connections

    var wireState = false
        private set

    fun connectTo(connectTo: Connection) {
        val newConnection = Connection("", connectTo)
        _connections.add(newConnection)
        onConnectionsChange(Unit)

        //todo best way to remove this listener on disconnect
        newConnection.onChange += {
            wireState = connections.any { c -> c.state }
            connections.forEach { connection ->

                //make sure connections don't circularly update themselves
                if (connection != newConnection) {
                    connection.state = wireState
                }
            }
        }
    }

//    fun connectTo(otherWire: Wire) {
//        val newConnection = Connection()
//        otherWire.connectTo(newConnection)
//        _connections.add(newConnection)
//
//        newConnection.onChange += {newState ->
//            connections.forEach { connection ->
//                //make sure connections don't circularly update themselves
//                if (connection != newConnection) {
//                    connection.state = newState
//                }
//            }
//        }
//    }
}