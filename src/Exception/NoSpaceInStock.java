package Exception;

public class NoSpaceInStock extends RuntimeException {
    public NoSpaceInStock(String message) {
        super(message);
    }
}
