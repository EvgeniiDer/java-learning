package com.example.inheritance.moneytask3;

public class Money {
    private long wholePart;
    private byte fractionalPart;

    public Money(long wholePart, byte fractionalPart) {
        if (fractionalPart < 0 || fractionalPart > 99) {
            throw new IllegalArgumentException("Дробная часть (копейки) должна быть в диапазоне от 0 до 99.");
        }
        this.wholePart = wholePart;
        this.fractionalPart = fractionalPart;
    }

    public void setWholePart(long wholePart) {
        this.wholePart = wholePart;
    }
    public void setFractionalPart(byte fractionalPart) {
        if (fractionalPart < 0 || fractionalPart > 99) {
            throw new IllegalArgumentException("Дробная часть (копейки) должна быть в диапазоне от 0 до 99.");
        }
        this.fractionalPart = fractionalPart;
    }
    public long getWholePart() {
        return wholePart;
    }
    public byte getFractionalPart() {
        return fractionalPart;
    }
    public void displayAmount() {
        System.out.printf("%d.%02d\n", this.wholePart, this.fractionalPart);
    }
}