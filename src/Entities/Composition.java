package Entities;

import java.util.Objects;

public class Composition {
    private final String title;
    private final double volume;
    private final ProductType type;

    public Composition(String title, double volume, ProductType type) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Введіть коректну назву!");
        }
        this.title = title;
        this.volume = volume;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public double getVolume() {
        return volume;
    }

    public ProductType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Назва: %-5s | Об'єм: %-5.2f м3 | Тип товарів: %-5s", title, volume, type.getType());
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Composition that = (Composition) o;
        return Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title);
    }
}
