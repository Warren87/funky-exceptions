package funkies;

import java.io.Serializable;

public interface SystemErrorCode extends Serializable {
    
    int getCode();
    
    default String name() {
        return Integer.toString(getCode());
    }
    
    default String getMessage() {
        return "System Error";
    }
    
    
}
