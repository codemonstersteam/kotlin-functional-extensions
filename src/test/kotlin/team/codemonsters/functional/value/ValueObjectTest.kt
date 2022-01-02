package team.codemonsters.functional.value

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import team.comdemonsters.functional.value.common.Email

class ValueObjectTest {

    @Test
    fun successfulEmailCreation() {
        val emailResult = Email.create("example@gmail.com")
        assertThat(emailResult.isSuccess()).isTrue
    }

    @Test
    fun failEmailCreation() {
        val emailResult = Email.create("example@gmail")
        assertThat(emailResult.isSuccess()).isFalse
        assertThat(emailResult.isFail()).isTrue
        assertThat(emailResult.error).isEqualTo("Email is invalid")
    }

}