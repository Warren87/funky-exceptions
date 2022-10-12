package funkies;

import funkies.errors.DataError;
import funkies.errors.UserError;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class SystemExceptionTest {
    
    @Test
    void customBuilderWorks(){
        SystemException.builder()
                .code(UserError.ACCESS_DENIED)
                .buildAndLog();
        
        SystemException.builder()
                .code(DataError.NOT_EXISTS)
                .buildAndLog(UUID.randomUUID());
        
        SystemException.builder().code(() -> 200).build();
    }

}