package funkies;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static funkies.LocalizedErrorMessages.Lang.EN;
import static funkies.LocalizedErrorMessages.Lang.PL;
import static funkies.errors.UserError.ACCESS_DENIED;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class LocalizedSystemExceptionTest {

    @ParameterizedTest
    @MethodSource
    void localizedSystemExceptionIsThrowable(LocalizedErrorMessages.Lang givenLang, String expectedErrorMessage){
        //when
        var localizedException = LocalizedSystemException.localized(givenLang, ACCESS_DENIED);
        
        //then
        assertThatCode( () -> { throw localizedException; })
                .extracting(LocalizedSystemException.class::cast)
                .satisfies( ex -> {
                    assertThat(ex.getCode()).isEqualTo(ACCESS_DENIED);
                    assertThat(ex.getMessage()).isEqualTo(expectedErrorMessage);
                });
    }

    static Stream<Arguments> localizedSystemExceptionIsThrowable() {
        return Stream.of(
                Arguments.of(EN, "[Localized] Access is denied"),
                Arguments.of(PL, "[Localized] Dostęp zabroniony")
        );
    }
}