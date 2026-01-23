package Entities;

public class Composition {
    private final String title;
    private final double volume;

    public Composition(String title, double volume) {
        if(title == null || title.isBlank()){
            throw new IllegalArgumentException("Введіть коректну назву!");
        }
        this.title = title;
        this.volume = volume;
    }

    public String getTitle() {
        return title;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return String.format("Назва: %-5s | Об'єм: %-5.2f м3 ", title, volume);
    }
}
