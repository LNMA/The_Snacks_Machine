package com.progressoft.induction;

public class Snack {
    private int availableQuantity;
    private Money price;

    public int quantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Money getPrice() {
        return price;
    }

    public void setPrice(Money price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Snack{" +
                "availableQuantity=" + availableQuantity +
                ", price=" + price +
                '}';
    }
}
