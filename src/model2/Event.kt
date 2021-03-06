package model2

class Event<T> {
    private val handlers = arrayListOf<(T) -> Unit>()
    operator fun plusAssign(handler: (T) -> Unit) {
        handlers.add(handler)
    }

    operator fun minusAssign(handler: (T) -> Unit) {
        handlers.remove(handler)
    }

    operator fun invoke(param: T) {
        handlers.forEach { it(param) }
    }
}