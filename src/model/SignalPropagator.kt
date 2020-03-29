package model

interface SignalPropagator {

    /**
     * Tracks whether this object has already propagated its signal to surrounding wires this update cycle.
     * Prevents propagation loops where adjacent wires continually update each other.
     */
    var propagatedThisTick: Boolean

    /**
     * This is called by other SignalPropagators to signal that this SignalPropagator
     * should take and propagate a signal.
     * This method should then call propagateTrueSignal on any connected propagators
     */
    fun propagateTrueSignal()
}