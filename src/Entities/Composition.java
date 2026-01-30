package Entities;

import java.util.Objects;

public class Composition {
    private int id;
    private final String title;
    private final double volume;
    private final ProductType type;

    public Composition(int id, String title, double volume, ProductType type) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Введіть коректну назву!");
        }
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("ID: %-3d | Назва: %-5s | Об'єм: %-5.2f м3 | Тип товарів: %-5s", id, title, volume, type.getType());
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
