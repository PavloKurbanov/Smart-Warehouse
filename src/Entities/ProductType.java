package Entities;

public enum ProductType {
    FRAGILE("Крихкий"),
    STABLE("Стабільний");

    private final String productType;
    ProductType(String productType) {
        this.productType = productType;
    }

    public String getType() {
        return productType;
    }
}
