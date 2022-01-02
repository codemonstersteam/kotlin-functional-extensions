package team.comdemonsters.functional.domain
import java.io.Serializable


class DomainResult<T> : Serializable {

    private val _result: T?
    private val _error: String?

    private constructor(error: String) {
        _result = null
        _error = error
    }

    private constructor(result: T) {
        _result = result
        _error = null
    }

    fun isFail(): Boolean = null == _result
    fun isSuccess(): Boolean = !isFail()
    val result: T get() = _result ?: throw NoSuchElementException("Result is failed and have no value")
    val error: String get() = _error ?: throw NoSuchElementException("Result is success and have no error")

    companion object {
        fun <T> success(result: T): DomainResult<T> = DomainResult(result = result)
        fun <T> fail(error: String): DomainResult<T> = DomainResult(error = error)
        fun <T> fromNullable(result: T?, errorMessage: String) : DomainResult<T> =
            if (null == result)
                DomainResult.fail(errorMessage)
            else
                DomainResult.success(result)
    }

}