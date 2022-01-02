package team.codemonsters.functional.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import team.comdemonsters.functional.domain.DomainResult

class DomainResultTest {

    @Test
    fun success() {
        val success = DomainResult.success<String>(result = "Hello success")
        assertThat(success.isSuccess()).isTrue
        assertThat(success.isFail()).isFalse
        assertThat(success.result).isEqualTo("Hello success")
    }

    @Test
    fun fail() {
        val fail = DomainResult.fail<String>(error = "Error message")
        assertThat(fail.isSuccess()).isFalse
        assertThat(fail.isFail()).isTrue
        assertThat(fail.error).isEqualTo("Error message")
        assertThatThrownBy { fail.result }
            .hasMessage("Result is failed and have no value")
    }

    @Test
    fun fromNullableSuccess() {
        val fromNullable = DomainResult.fromNullable(result = "Hello Success", "Result cant be empty")
        assertThat(fromNullable.isSuccess()).isTrue
    }

}