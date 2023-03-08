package funkies.errors;

import java.io.Serializable;


public sealed interface SystemErrorCode extends Serializable
        permits DataError, UserError {

    int getCode();

    default String name() {
        return Integer.toString(getCode());
    }

    default String getMessage() {
        return "System Error";
    }


}
