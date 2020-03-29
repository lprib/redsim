package model

/** A wire used to propagate signals */
class Wire : SimItem, SignalPropagator {
    /** The current outward observable state of the wire */
    var state = false

    /**
     * Components can queue a state during a clock update.
     * The wire and all connected wires will take this state on resolveWires().
     */
    private var queuedState = false


    override var propagatedThisTick = false

    /** This wire will propagate its signal to all connectedWires */
    val connectedWires = mutableListOf<SignalPropagator>()

    override fun clock() {
        propagatedThisTick = false
    }

    override fun resolveWires() {
        //This wire may have already been propagated from other wires, so only update if not already propagated
        if (!propagatedThisTick) {
            state = queuedState
            queuedState = false
            if (state) propagateTrueSignal()
        }
    }

    override fun propagateTrueSignal() {
        if (!propagatedThisTick) {
            state = true
            propagatedThisTick = true
            connectedWires.forEach { it.propagateTrueSignal() }
        }
    }

    /**
     * Queue a value for this wire to take next time resolveWires() is called.
     * The update is deferred until resolveWires() to prevent race conditions and update loops.
     *
     * Queueing a value of false will have not effect is this wire has already been queued to take a true value.
     * True always has precedence over false.
     */
    fun queueValue(value: Boolean) {
        queuedState = queuedState or value
    }
}