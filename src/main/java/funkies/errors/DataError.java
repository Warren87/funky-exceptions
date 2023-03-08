package funkies.errors;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//200-299
@Getter
@RequiredArgsConstructor
public enum DataError implements SystemErrorCode {
    NOT_EXISTS(200, "Data with id [{}] doesn't exists"),
    NO_ACCESS(201, "Access to data is forbidden");

    final int code;

    final String message;

}
