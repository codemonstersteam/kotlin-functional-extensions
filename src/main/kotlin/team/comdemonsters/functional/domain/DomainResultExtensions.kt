package team.comdemonsters.functional.domain

inline fun <T> DomainResult<T>.ensure(predicate: (value: T) -> Boolean, errorMessage: String): DomainResult<T> {
    if (this.isFail())
        return this
    if (!predicate(this.result))
        return DomainResult.fail(errorMessage)
    return this
}

inline fun <R, T> DomainResult<T>.map(transform: (value: T) -> R): DomainResult<R> {
    return when {
        isSuccess() -> DomainResult.success(transform(this.result as T))
        else -> DomainResult.fail(error)
    }
}
