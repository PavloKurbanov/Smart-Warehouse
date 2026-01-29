package Repository;

import Entities.Composition;
import Entities.ProductType;
import Entities.Products;
import Repository.CompositionRepository;
import Repository.ProductsRepository;

import java.util.Arrays;
import java.util.function.Predicate;

public class InMemoryProductsRepository implements ProductsRepository {

    private Products[] products;
    private int productCount;
    private int id = 1;

    public InMemoryProductsRepository() {
        int capacity = 3;
        this.products = new Products[capacity];
    }

    @Override
    public Products save(Products product) {
        if (productCount == products.length){
            int resize = 2;
            products = Arrays.copyOf(products,  products.length * resize);
        }
        product.setId(id++);
        products[productCount++] = product;
        return product;
    }

    @Override
    public Products findById(Integer id) {
        for (int i = 0; i < productCount; i++) {
            if(products[i].getId() == id){
                return products[i];
            }
        }
        throw new IllegalArgumentException("Не має продутку з ID: " + id);
    }

    @Override
    public Products findByName(String name) {
        for (int i = 0; i < productCount; i++) {
            if(products[i].getTitle().equals(name)){
                return products[i];
            }
        }
        throw new IllegalArgumentException("Не має продукту з іменем " + name);
    }

    @Override
    public Products[] findByType(ProductType productType) {
        return result(p -> p.getType() == productType);
    }

    @Override
    public Products[] findAll() {
        return Arrays.copyOf(products, productCount);
    }

    @Override
    public Products[] findByCompositionTitle(String title) {
        return result(p -> p.getCompositionTitle() != null && p.getCompositionTitle().equals(title));
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