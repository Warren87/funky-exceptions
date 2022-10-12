package funkies.errors;

import funkies.SystemCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//100-199
@Getter
@RequiredArgsConstructor
public enum UserError implements SystemCode {
    ACCESS_DENIED(100, "User has not required roles");

    final int code;

    final String message;

}
