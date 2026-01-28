package Entities;

import java.util.Objects;

public class Products {
    private Integer id;
    private final String title;
    private final double height;
    private final double weight;
    private final double length;
    private final ProductType type;
    private final int price;

    public Products(int id,  String title, double height, double weight, double length, ProductType type, int price) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Введіть коректну назву!");
        }
        if (height <= 0 || height > 100) {
            throw new IllegalArgumentException("Висота не може бути менше 0 та більша 100");
        }
        if (length <= 0 || length > 100) {
            throw new IllegalArgumentException("Довжина не може бути менше 0 та більша 100");
        }
        if (weight <= 0 || weight > 100) {
            throw new IllegalArgumentException("Ширина не може бути менше 0 та більша 100");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Ціна не може бути менше або дорівнювати 0");
        }
        this.id = id;
        this.title = title;
        this.height = height;
        this.weight = weight;
        this.length = length;
        this.type = type;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getLength() {
        return length;
    }

    public ProductType getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public String getCompositionTitle() {
        return compositionTitle;
    }

    public double getVolume() {
        return length * weight * height;
    }

    @Override
    public String toString() {
        return String.format("Назва: %-5s | Об'єм: %-5f | Тип: %-5s | Ціна: %-5d | Склад: %-5s", title, getVolume(), type.getType(), price, compositionTitle);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Products products = (Products) o;
        return Double.compare(height, products.height) == 0 && Double.compare(weight, products.weight) == 0 &&
                Double.compare(length, products.length) == 0 && price == products.price && Objects.equals(title, products.title) && type == products.type &&
                Objects.equals(compositionTitle, products.compositionTitle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, height, weight, length, type, price, compositionTitle);
    }
}
