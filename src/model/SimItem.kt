package model

/** An item in the simulation which is clocked and/or propagates signals */
interface SimItem {
    /**
     * Generally used in components to do logic and queue updates to surrounding wires.
     * This is called only once per clock cycle per SimItem.
     */
    fun clock()

    /**
     * Signals a SimItem to resolve and propagate wire signals after all components have clocked.
     * SimItems may propagate signals to connected SimItems during this time, although resolveWires() should only
     * be called once per clock cycle per item.
     */
    fun resolveWires()
}