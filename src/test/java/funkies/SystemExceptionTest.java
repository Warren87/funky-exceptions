package funkies;

import funkies.errors.DataError;
import funkies.errors.UserError;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;


class SystemExceptionTest {


    @Test
    void systemExceptionIsThrowable() {

        var systemException = SystemException.builder()
                .code(UserError.ACCESS_DENIED)
                .buildAndLog();

        assertThatCode(() -> {
            throw systemException;
        })
                .extracting(SystemException.class::cast)
                .extracting(SystemException::getCode)
                .isEqualTo(UserError.ACCESS_DENIED);
    }

    @Test
    void differentCodesMakesDifferentExceptions() {
        var deniedException = SystemException.builder()
                .code(UserError.ACCESS_DENIED)
                .buildAndLog();

        var notExistsException = SystemException.builder()
                .code(DataError.NOT_EXISTS)
                .buildAndLog(UUID.randomUUID());


        assertThat(deniedException)
                .isNotEqualTo(notExistsException);
    }


    @Test
    void sameCodesMakesSameExceptions() {
        var deniedException = SystemException.builder()
                .code(UserError.ACCESS_DENIED)
                .buildAndLog();

        var anotherDeniedException = SystemException.builder()
                .code(UserError.ACCESS_DENIED)
                .buildAndLog();

        assertThat(deniedException)
                .isEqualTo(anotherDeniedException);
    }

    @Test
    void systemExceptionHasParametrizedMessages() {
        var idToLog = 10;
        var notExistsException = SystemException.builder()
                .code(DataError.NOT_EXISTS)
                .buildAndLog(idToLog);

        assertThat(notExistsException.getMessage())
                .isEqualTo("Data with id [{}] doesn't exists");
    }


}