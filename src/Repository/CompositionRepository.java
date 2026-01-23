package Repository;

import Entities.Composition;

public interface CompositionRepository {
    Composition save(Composition composition);
    Composition findByName(String name);
    Composition[] findAll();
}
