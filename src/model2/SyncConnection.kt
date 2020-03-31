package model2

class SyncConnection(name: String, connectedTo: Connection? = null) : Connection(name, connectedTo) {
    private var queuedState = false
    override var state: Boolean
        get() = super.state
        set(value) {
            println("queue update")
            queuedState = value
        }

    override fun resolveWires() {
        super.state = queuedState
    }
}