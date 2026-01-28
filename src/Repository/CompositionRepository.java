package Repository;

import Entities.Composition;
import Entities.ProductType;

public interface CompositionRepository {
    Composition save(Composition composition);

    Composition findByTitle(String title);

    Composition findById(Integer id);

    Composition[] findAll();

    Composition[] findByType(ProductType type);
}
