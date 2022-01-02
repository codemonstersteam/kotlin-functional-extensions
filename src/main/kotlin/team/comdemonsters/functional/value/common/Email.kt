package team.comdemonsters.functional.value.common

import team.comdemonsters.functional.domain.DomainResult
import team.comdemonsters.functional.domain.ensure
import team.comdemonsters.functional.domain.map
import team.comdemonsters.functional.value.ValueObject

class Email<String> private constructor(value: String) : ValueObject<String>(value) {

    companion object {
        private val emailRegexRFC5322: Regex =
            Regex("(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")

        fun create(emailOrNothing: String?): DomainResult<Email<String>> =
            DomainResult.fromNullable(emailOrNothing, "Email should not be null")
                .map { it.trim() }
                .ensure({
                    it.isNotEmpty()
                }, "Email should not be empty")
                .ensure({ it.length <= 256 }, "Email is too long")
                .ensure({ emailRegexRFC5322.matches(it) }, "Email is invalid")
                .map { email -> Email(email) }
    }

    override fun equalsCore(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Email<*>) return false
        return value == other.value
    }

    override fun hashCodeCore(): Int = value.hashCode()
}
