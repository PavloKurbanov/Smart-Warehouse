package Repository;

import Entities.Composition;
import Entities.ProductType;

import java.util.Arrays;
import java.util.function.Predicate;

public class InMemoryCompositionRepository implements CompositionRepository {

    private Composition[] compositions;
    private int compositionCount;
    private int id = 1;

    public InMemoryCompositionRepository() {
        int capacity = 3;
        this.compositions = new Composition[capacity];
    }

    @Override
    public Composition save(Composition composition) {
        if (compositionCount == compositions.length) {
            int resize = 2;
            compositions = Arrays.copyOf(compositions, compositions.length * resize);
        }
        composition.setId(id++);
        compositions[compositionCount++] = composition;
        return composition;
    }

    @Override
    public Composition findById(Integer id) {
        for (int i = 0; i < compositionCount; i++) {
            if(compositions[i].getId() == id){
                return compositions[i];
            }
        }
        throw new IllegalArgumentException("Не має складу з ID: " + id);
    }

    @Override
    public Composition findByTitle(String title) {
        for (int i = 0; i < compositionCount; i++) {
            Composition currentComposition = compositions[i];
            if (currentComposition.getTitle().equals(title)) {
                return currentComposition;
            }
        }
        throw new IllegalArgumentException("Не має товару " + title + " в складі " + Composition.class.getSimpleName());
    }

    @Override
    public Composition[] findAll() {
        return Arrays.copyOf(compositions, compositionCount);
    }

    @Override
    public Composition[] findByType(ProductType type) {
        return result(Composition -> Composition.getType() == type);
    }

    public Composition[] result(Predicate<Composition> composition) {
        int count = 0;

        for (int i = 0; i < compositionCount; i++) {
            if (compositions[i] != null && composition.test(compositions[i])) {
                count++;
            }
        }

        Composition[] newComposition = new Composition[count];
        int index = 0;

        for (int i = 0; i < compositionCount; i++) {
            if (compositions[i] != null && composition.test(compositions[i])) {
                newComposition[index++] = compositions[i];
            }
        }
        return newComposition;
    }
}
