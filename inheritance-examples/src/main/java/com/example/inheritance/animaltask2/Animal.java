package com.example.inheritance.animaltask2;

public abstract class Animal {
    protected String name;
    protected String characteristics;

    public Animal(String name, String characteristics) {
        this.name = name;
        this.characteristics = characteristics;
    }

    public void eat() {
        System.out.println(name + " кушает.");
    }
    public abstract void sound();

    public void showInfo() {
        System.out.println("Животное: " + name + ". Описание: " + characteristics);
    }
}