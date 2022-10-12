package funkies;

@FunctionalInterface
public interface SystemCode {
    
    int getCode();
    
    default String name() {
        return "N/A";
    }
    
    default String getMessage() {
        return "System Error";
    }
    
}
