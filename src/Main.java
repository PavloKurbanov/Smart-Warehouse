import Repository.CompositionRepository;
import Repository.InMemoryCompositionRepository;
import Repository.InMemoryProductsRepository;
import Repository.ProductsRepository;
import Service.WarehouseService;
import Service.WarehouseServiceImpl;

public class Main {
    public static void main(String[] args) {
        CompositionRepository compositionRepository = new InMemoryCompositionRepository();
        ProductsRepository productsRepository = new InMemoryProductsRepository();
        WarehouseService warehouseService = new WarehouseServiceImpl(productsRepository, compositionRepository);
        ConsoleUI consoleUI = new ConsoleUI(warehouseService);

        consoleUI.start();
    }
}