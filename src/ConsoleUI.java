import Entities.Composition;
import Entities.ProductType;
import Entities.Products;
import Service.WarehouseService;

import java.util.Scanner;

public class ConsoleUI {
    private final WarehouseService warehouseService;
    private Scanner scanner = new Scanner(System.in);

    public ConsoleUI(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    public void start() {
        while (true) {
            System.out.println("1) Додати продукт");
            System.out.println("2) Додати склад");
            System.out.println("3) На скільки зайнятий склад");
            System.out.println("4) На скільки вільний склад");
            System.out.println("5) Показати всі продукти");
            System.out.println("6) Показати всі склади");
            System.out.println("7) Показати всі продукти в складі");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addProducts();
                    break;
                case "2":
                    addComposition();
                    break;
                case "3":
                    howVolumeComposition();
                    break;
                case "4":
                    howAvailabilityComposition();
                    break;
                case "5":
                    printAllProducts();
                    break;
                case "6":
                    printAllCompositions();
                    break;
                case "7":
                    allProductsByComposition();
                    break;
                default:
                    System.out.println("Йдіть нахуй");
                    break;
            }
        }
    }

    private void addProducts() {
        System.out.println("Введіть назву продукту:");
        String name = scanner.nextLine();
        System.out.println("Введіть висоту, вагу та довжину:");
        String param = scanner.nextLine();
        String[] split = param.split(",");
        double height = Double.parseDouble(split[0].trim());
        double weight = Double.parseDouble(split[1].trim());
        double length = Double.parseDouble(split[2].trim());
        ProductType productType = getProductType();
        System.out.println("Введіть ціну продукту: ");
        Integer quantity = scanner.nextInt();
        warehouseService.autoRegisterProduct(new Products(0, name, height, weight, length, productType, quantity, null));
    }

    private void addComposition() {
        System.out.println("Введіть назву складу: ");
        String name = scanner.nextLine();
        System.out.println("Введіть ємність складу: ");
        Double volume = Double.parseDouble(scanner.nextLine());
        ProductType productType = getProductType();
        warehouseService.createWarehouse(new Composition(0, name, volume, productType));
    }

    private void howVolumeComposition() {
        Integer id = getId();
        double warehouseLoadPercentage = warehouseService.getWarehouseLoadPercentage(id);
        Composition composition = warehouseService.getComposition(id);
        System.out.println("Склад " + composition.getTitle() + " зайнятий на " + warehouseLoadPercentage + "%");
    }


    private void howAvailabilityComposition() {
        Integer id = getId();
        double availableSpace = warehouseService.getAvailableSpace(id);
        Composition composition = warehouseService.getComposition(id);
        System.out.println("Склад " + composition.getTitle() + " вільний на " + availableSpace + "%");
    }

    private void printAllProducts() {
        System.out.println("Продукти:");
        Products[] allProducts = warehouseService.getAllProducts();
        for (Products product : allProducts) {
            System.out.println(product);
        }
    }

    private void printAllCompositions() {
        System.out.println("Склади:");
        Composition[] allWarehouses = warehouseService.getAllWarehouses();
        for (Composition composition : allWarehouses) {
            System.out.println(composition);
        }
    }

    private void allProductsByComposition() {
        Integer id = getId();
        Products[] productsByWarehouse = warehouseService.getProductsByWarehouse(getId());
        for (Products products : productsByWarehouse) {
            System.out.println(products);
        }
    }

    private ProductType getProductType() {
        ProductType[] productTypes = ProductType.values();
        ProductType productType = null;
        do {
            System.out.println("Виберіть тип: ");
            for (int i = 0; i < productTypes.length; i++) {
                System.out.println((i + 1) + ". " + productTypes[i].getType());
            }
            Integer productTypeIndex = scanner.nextInt();
            if (productTypeIndex >= 0 && productTypeIndex <= productTypes.length) {
                productType = productTypes[productTypeIndex - 1];
            }
        } while (productType == null);
        return productType;
    }

    private Integer getId() {
        Composition[] allWarehouses = warehouseService.getAllWarehouses();
        for (Composition composition : allWarehouses) {
            System.out.println(composition);
        }
        Integer id = Integer.parseInt(scanner.nextLine());
        return id;
    }
}

