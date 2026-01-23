package Repository;

import Entities.Composition;
import Entities.ProductType;
import Entities.Products;

import java.util.function.Predicate;

public class InMemoryProductsRepository implements CompositionRepository {
    private final static int CAPACITY = 3;
    private final static int RESIZE = 2;

    private Products[] products;
    int productCount;

    public InMemoryProductsRepository() {
        this.products = new Products[CAPACITY];
    }

    @Override
    public Composition save(Composition composition) {
        return null;
    }

    @Override
    public Composition findByTitle(String title) {
        return null;
    }

    @Override
    public Composition[] findAll() {
        return new Composition[0];
    }

    @Override
    public Composition[] findByType(ProductType type) {
        return new Composition[0];
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
