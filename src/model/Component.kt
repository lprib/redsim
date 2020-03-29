package model

import java.lang.IllegalArgumentException

/** A general component that has a number of inputs and output wires. */
abstract class Component : SimItem {
    /** Inputs are not owned by the component, only connected up by reference */
    abstract val inputs: List<InputWire>
    /** Outputs are wires that are owned by this object */
    abstract val outputs: List<OutputWire>

    /**
     * Returns the input wire of specified name if it is connected, else null.
     * @throws IllegalArgumentException if there is no input wire (connected or not) with the name.
     */
    fun input(name: String): Wire? =
        (inputs.find { it.name == name } ?: throw IllegalArgumentException("input `$name` does not exist")).wire

    /**
     * Connects the input of specified name to the specified wire. Set to null to disconnect the input
     * @throws IllegalArgumentException if there is no input wire (connected or not) with the name.
     */
    fun setInput(name: String, wire: Wire) {
        (inputs.find { it.name == name } ?: throw IllegalArgumentException("input `$name` does not exist")).wire = wire
        onInputWireChange()
    }

    /**
     * Returns the output wire of specified name.
     * @throws IllegalArgumentException if there is no output wire with the name.
     */
    fun output(name: String): Wire =
        (outputs.find { it.name == name } ?: throw IllegalArgumentException("output `$name` does not exist")).wire

    override fun clock() {
        outputs.forEach { it.wire.clock() }
    }

    override fun resolveWires() {
        outputs.forEach { it.wire.resolveWires() }
    }

    /** Called whenever an input is connected or disconnected */
    open fun onInputWireChange() {}
}

/** Input wire may or may not be connected, so has a nullable member */
data class InputWire(val name: String, var wire: Wire? = null)

/** Output wire is owned by the parent component, and always exists, hence non-nullable member */
data class OutputWire(val name: String, val wire: Wire = Wire())