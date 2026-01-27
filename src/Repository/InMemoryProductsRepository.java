package Repository;

import Entities.Composition;
import Entities.ProductType;
import Entities.Products;
import Repository.CompositionRepository;
import Repository.ProductsRepository;

import java.util.function.Predicate;

public class InMemoryProductsRepository implements ProductsRepository {
    private final int CAPACITY = 3;
    private final int RESIZE = 2;

    private Products[] products;
    int productCount;

    public InMemoryProductsRepository() {
        this.products = new Products[CAPACITY];
    }

    @Override
    public Products save(Products products) {
        return null;
    }

    @Override
    public Products findById(String id) {
        return null;
    }

    @Override
    public Products findByName(String name) {
        return null;
    }

    @Override
    public Products[] findByType(ProductType productType) {
        return new Products[0];
    }

    @Override
    public Products[] findAll() {
        return new Products[0];
    }

    @Override
    public Products[] findByCompositionTitle(String title) {
        return new Products[0];
    }

    public Products[] result(Predicate<Products> composition) {
        int count = 0;

        for (int i = 0; i < productCount; i++) {
            if (products[i] != null && composition.test(products[i])) {
                count++;
            }
        }

        Products[] newProducts = new Products[count];
        int index = 0;

        for (int i = 0; i < productCount; i++) {
            if (products[i] != null && composition.test(products[i])) {
                newProducts[index++] = products[i];
            }
        }
        return newProducts;
    }
}