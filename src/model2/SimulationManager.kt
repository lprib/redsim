package model2

class SimulationManager {
    private val _clockableObjects = mutableListOf<Clockable>()
    val clockableObjects: List<Clockable> = _clockableObjects

    fun addObject(obj: Clockable) {
        _clockableObjects.add(obj)
    }

    fun clock() {
        clockableObjects.forEach { it.clock() }
    }
}