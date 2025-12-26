package com.example.inheritance.moneytask3;

public class Product {
    private String name;
    private Money price;

    public Product(String name, Money price) {
        this.name = name;
        this.price = price;
    }
    public void decreasePrice(Money discount) {
        long currentPriceInCents = price.getWholePart() * 100 + price.getFractionalPart();
        long discountInCents = discount.getWholePart() * 100 + discount.getFractionalPart();
        long newPriceInCents = currentPriceInCents - discountInCents;

        if (newPriceInCents < 0) {
            System.out.println("Ошибка: Скидка больше текущей цены. Цена установлена в 0.00.");
            this.price.setWholePart(0);
            this.price.setFractionalPart((byte) 0);
        } else {
            long newWholePart = newPriceInCents / 100;
            byte newFractionalPart = (byte) (newPriceInCents % 100);

            this.price.setWholePart(newWholePart);
            this.price.setFractionalPart(newFractionalPart);
        }
    }

    public void displayProductInfo() {
        System.out.println("Товар: " + this.name);
        System.out.print("Текущая цена: ");
        this.price.displayAmount(); // Используем метод из класса Money
    }
}