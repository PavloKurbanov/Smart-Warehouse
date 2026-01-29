package Exception;

public class StockNotFoundException extends RuntimeException {
    public StockNotFoundException(String productNotFound) {
        super(productNotFound);
    }
}
