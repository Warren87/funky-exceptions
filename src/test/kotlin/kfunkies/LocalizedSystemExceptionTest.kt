package kfunkies

import kfunkies.Lang.EN
import kfunkies.Lang.PL
import kfunkies.errors.UserError
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class LocalizedSystemExceptionTest {

    @ParameterizedTest
    @MethodSource("localizationErrorTestData")
    fun `Localized exception - same code different messages for lang`(givenLang: Lang, expectedErrorMessage: String) {
        //when
        val localizedException = LocalizedSystemException.localized(
            lang = givenLang, 
            errorCode = UserError.ACCESS_DENIED
        )

        //then
        assertThatThrownBy { throw localizedException }
            .extracting { it as LocalizedSystemException }
            .satisfies(
                { assertThat(it.errorCode).isEqualTo(UserError.ACCESS_DENIED) },
                { assertThat(it.message).isEqualTo(expectedErrorMessage) }
            )
    }

    companion object {
        @JvmStatic
        fun localizationErrorTestData() = listOf(
            EN withResult "[Localized] Access is denied",
            PL withResult "[Localized] Dostęp zabroniony"
        )
    }

}

infix fun Lang.withResult(message : String) = Arguments.of(this, message)  