package team.comdemonsters.functional.value

abstract class ValueObject<T> protected constructor(val value: T) {

    private var _cachedHashCode: Int? = null

    abstract fun equalsCore(other: Any?): Boolean

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        other as ValueObject<*>
        return equalsCore(other)
    }

    abstract fun hashCodeCore(): Int

    override fun hashCode(): Int {
        if(null!= _cachedHashCode)
            return _cachedHashCode!!;
        _cachedHashCode = hashCodeCore();
        return _cachedHashCode!!;
    }

}
