package model2

open class Connection(val name: String = "", var connectedTo: Connection? = null): Clockable {
    init {
        //make connections 2-way reference on instantiation
        connectedTo?.connectedTo = this
    }

    private var internalState = false
    open var state: Boolean
        get() = internalState
        set(value) {
            println("instant wire update")
            val prevValue = internalState
            internalState = value
            connectedTo?.internalState = value
            if(prevValue != value) {
                connectedTo?.onChange?.invoke(value)
            }
        }

    val onChange = Event<Boolean>()
}