package funkies.errors;

import funkies.SystemCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//200-299
@Getter
@RequiredArgsConstructor
public enum DataError implements SystemCode {
    NOT_EXISTS(200, "Data with id [{}] doesn't exists");

    final int code;

    final String message;

}
