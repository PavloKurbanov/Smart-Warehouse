package Entities;

public class Products {
    private final String title;
    private final double height;
    private final double weight;
    private final double length;
    private final ProductType type;
    private final int price;

    public  Products(String title, double height, double weight, double length, ProductType type, int price) {
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Введіть коректну назву!");
        }
        if(height <= 0 || height > 100) {
            throw new IllegalArgumentException("Висота не може бути менше 0 та більша 100");
        }
        if(length <= 0 || length > 100) {
            throw new IllegalArgumentException("Довжина не може бути менше 0 та більша 100");
        }
        if(weight <= 0 || weight > 100) {
            throw new IllegalArgumentException("Ширина не може бути менше 0 та більша 100");
        }
        if(price <= 0) {
            throw new IllegalArgumentException("Ціна не може бути менше або дорівнювати 0");
        }
        this.title = title;
        this.height = height;
        this.weight = weight;
        this.length = length;
        this.type = type;
        this.price = price;
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

    public double getVolume(){
        return length *  weight * height;
    }

    @Override
    public String toString() {
        return String.format("Назва: %-5s | Об'єм: %-5f | Тип: %-5s | Ціна: %-5d", title, getVolume(), type.getType(), price);
    }
}
