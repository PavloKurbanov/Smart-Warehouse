package Service;

import Entities.Composition;
import Entities.ProductType;
import Entities.Products;

public interface WarehouseService {
    // 1. Управління (Actions)
    void autoRegisterProduct(Products product);
    void createWarehouse(Composition composition);

    // 2. Аналітика (Reporting)
    double getWarehouseLoadPercentage(int warehouseId);
    double getAvailableSpace(int warehouseId);

    // 3. Доступ до даних (Data Access)
    Products[] getAllProducts();
    Composition[] getAllWarehouses();
    Products[] getProductsByWarehouse(int warehouseId);
}
