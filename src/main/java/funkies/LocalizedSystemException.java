package funkies;

import funkies.LocalizedErrorMessages.Lang;
import funkies.errors.SystemErrorCode;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@EqualsAndHashCode(callSuper = true)
public class LocalizedSystemException extends RuntimeException {

    SystemErrorCode code;

    private LocalizedSystemException(SystemErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    static LocalizedSystemException localized(Lang lang, SystemErrorCode systemErrorCode) {
        var errorMessage = LocalizedErrorMessages.message(lang, systemErrorCode.name(),
                "System Error. Missing error mapping for [" + systemErrorCode.name() + "]");
        return new LocalizedSystemException(systemErrorCode, errorMessage);
    }


}
