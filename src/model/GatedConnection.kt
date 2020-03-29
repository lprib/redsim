package model

class GatedConnection: Component(), SignalPropagator {
    override val inputs = listOf(InputWire("w1"), InputWire("w2"), InputWire("gate"))
    override val outputs: List<OutputWire> = listOf()

    private val active get() = input("gate")?.state ?: false

    override var propagatedThisTick = false

    override fun propagateTrueSignal() {
        if(!propagatedThisTick and active) {
            propagatedThisTick = true
            inputs.map { it.wire }.forEach{it?.propagateTrueSignal()}
        }
    }
}