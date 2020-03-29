package model

/** A component that takes a single input, performs an operation, and has a single output */
abstract class UnaryOperationComponent : Component() {
    override val inputs = listOf(InputWire("a"))
    override val outputs = listOf(OutputWire("o"))

    private var a: Wire? = null
    private val o = output("o")

    override fun clock() {
        super.clock()
        o.queueValue(operation(a?.state ?: false))
    }

    /** The operation that will be performed on the input to become the output */
    abstract fun operation(input: Boolean): Boolean

    override fun onInputWireChange() {
        a = input("a")
    }
}

class NotGate: UnaryOperationComponent() {
    override fun operation(input: Boolean): Boolean = !input
}

class Buffer: UnaryOperationComponent() {
    override fun operation(input: Boolean): Boolean = input
}