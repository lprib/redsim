package model

/**
 * A component that takes two inputs, and outputs a binary operation on them
 */
abstract class BinaryOperationComponent : Component() {
    override val inputs = listOf(InputWire("a"), InputWire(("b")))
    override val outputs = listOf(OutputWire("o"))

    private var a: Wire? = null
    private var b: Wire? = null
    private val o = output("o")

    override fun clock() {
        super.clock()
        o.queueValue(operation(a?.state ?: false, b?.state ?: false))
    }

    /**
     * The operation that will be performed
     */
    abstract fun operation(a: Boolean, b: Boolean): Boolean

    override fun onInputWireChange() {
        a = input("a")
        b = input("b")
    }
}

class AndGate: BinaryOperationComponent() {
    override fun operation(a: Boolean, b: Boolean): Boolean = a and b
}

class OrGate: BinaryOperationComponent() {
    override fun operation(a: Boolean, b: Boolean): Boolean = a or b
}

class NandGate: BinaryOperationComponent() {
    override fun operation(a: Boolean, b: Boolean): Boolean = !(a and b)
}

class NorGate: BinaryOperationComponent() {
    override fun operation(a: Boolean, b: Boolean): Boolean = !(a or b)
}

class XorGate: BinaryOperationComponent() {
    override fun operation(a: Boolean, b: Boolean): Boolean = a xor b
}

class XnorGate: BinaryOperationComponent() {
    override fun operation(a: Boolean, b: Boolean): Boolean = !(a xor b)
}