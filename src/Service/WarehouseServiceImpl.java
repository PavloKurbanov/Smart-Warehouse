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
        if (composition == null) {
            throw new IllegalArgumentException("Склад не може бути null");
        }

        if (composition.getVolume() < 0 || composition.getTitle().isBlank()) {
            throw new IllegalArgumentException("Введіть коректні дані");
        }
        try{
            compositionRepository.findByTitle(composition.getTitle());
        } catch (IllegalArgumentException e){
            compositionRepository.save(composition);
        }
    }

    @Override
    public Composition getComposition(int warehouseId) {
        return compositionRepository.findById(warehouseId);
    }

    @Override
    public double getWarehouseLoadPercentage(int warehouseId) {
        Composition composition = compositionRepository.findById(warehouseId);
        double count = calculateOccupiedVolume(composition.getTitle());
        return (count / composition.getVolume()) * 100;
    }

    @Override
    public double getAvailableSpace(int warehouseId) {
        Composition composition = compositionRepository.findById(warehouseId);
        double count = calculateOccupiedVolume(composition.getTitle());
        return composition.getVolume() - count;
    }

    @Override
    public Products[] getAllProducts() {
        return productsRepository.findAll();
    }

    @Override
    public Composition[] getAllWarehouses() {
        return compositionRepository.findAll();
    }

    @Override
    public Products[] getProductsByWarehouse(int warehouseId) {
        Products[] products = productsRepository.findByCompositionTitle(compositionRepository.findById(warehouseId).getTitle());
        return products;
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
