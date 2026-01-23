package Repository;

import Entities.ProductType;
import Entities.Products;

public interface ProductsRepository {
    Products save(Products products);

    Products findById(String id);

    Products findByName(String name);

    Products[] findByType(ProductType productType);

    Products[] findAll();

    Products[] findByCompositionTitle(String title);
}
