package kfunkies

import kfunkies.errors.DataError
import kfunkies.errors.UserError
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.util.*

class SystemExceptionTest {

    @Test
    fun systemExceptionIsThrowable() {
        val exception = SystemException.andLog(UserError.ACCESS_DENIED);

        assertThatThrownBy { throw exception }
            .extracting{ it as SystemException }
            .extracting{ it.errorCode }
            .isEqualTo(UserError.ACCESS_DENIED)
    }


    @Test
    fun differentCodesMakesDifferentExceptions() {
        val deniedException = SystemException(UserError.ACCESS_DENIED)
        val notExistsException = SystemException.andLog(DataError.NOT_EXISTS, UUID.randomUUID())
        Assertions.assertThat(deniedException)
            .isNotEqualTo(notExistsException)
    }
}