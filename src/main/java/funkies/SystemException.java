package funkies;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Builder
@Value
@EqualsAndHashCode(callSuper = false)
public class SystemException extends RuntimeException {

    SystemCode code;

    static class SystemExceptionBuilder {

        SystemException buildAndLog(Object... messageParams) {
            log.error("Error code " + code.getCode() + ": " + code.getMessage(), messageParams);
            return this.build();
        }
    }

}
