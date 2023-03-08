package funkies;

import funkies.errors.SystemErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Value
@EqualsAndHashCode(callSuper = false)
public class SystemException extends RuntimeException {

    SystemErrorCode code;

    SystemException(SystemErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public static SystemExceptionBuilder builder() {
        return new SystemExceptionBuilder();
    }

    static class SystemExceptionBuilder {

        private SystemErrorCode code;

        public SystemExceptionBuilder code(SystemErrorCode code) {
            this.code = code;
            return this;
        }

        SystemException buildAndLog(Object... messageParams) {
            log.error("Error code " + code.getCode() + ": " + code.getMessage(), messageParams);
            return new SystemException(code, code.getMessage());
        }

    }

}
