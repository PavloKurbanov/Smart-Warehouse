package Service;

import Entities.Composition;
import Entities.Products;
import Repository.CompositionRepository;
import Repository.ProductsRepository;
import Exception.StockNotFoundException;
import Exception.StorageFullException;

public class WarehouseServiceImpl implements WarehouseService {

    private final ProductsRepository productsRepository;
    private final CompositionRepository compositionRepository;

    public WarehouseServiceImpl(ProductsRepository productsRepository, CompositionRepository compositionRepository) {
        this.productsRepository = productsRepository;
        this.compositionRepository = compositionRepository;
    }

    @Override
    public void autoRegisterProduct(Products product) {

        Composition[] findCompositionType = compositionRepository.findByType(product.getType());

        if (findCompositionType.length == 0) {
            throw new StockNotFoundException("Складу для товару не знайдено");
        }

        for (int i = 0; i < findCompositionType.length; i++) {
            String title = findCompositionType[i].getTitle();
            double occupied = calculateOccupiedVolume(title);

            if (occupied + product.getVolume() <= findCompositionType[i].getVolume()) {
                product.setCompositionTitle(title);
                productsRepository.save(product);
                return;
            }
        }
        throw new StorageFullException("Помилка при збережені");
    }

    @Override
    public void createWarehouse(Composition composition) {

    }

    @Override
    public double getWarehouseLoadPercentage(int warehouseId) {
        return 0;
    }

    @Override
    public double getAvailableSpace(int warehouseId) {
        return 0;
    }

    @Override
    public Products[] getAllProducts() {
        return new Products[0];
    }

    @Override
    public Composition[] getAllWarehouses() {
        return new Composition[0];
    }

    @Override
    public Products[] getProductsByWarehouse(int warehouseId) {
        return new Products[0];
    }

    private double calculateOccupiedVolume(String compositionTitle) {
        Products[] products = productsRepository.findByCompositionTitle(compositionTitle);
        double total = 0;
        for (int i = 0; i < products.length; i++) {
            total += products[i].getVolume();
        }
        return total;
    }
}
