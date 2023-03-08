package funkies.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//100-199
@Getter
@RequiredArgsConstructor
public enum UserError implements SystemErrorCode {
    ACCESS_DENIED(100, "Access is denied for this user");

    final int code;

    final String message;

}
