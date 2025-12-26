package com.example.inheritance.animaltask2;


public class Tiger extends Animal {

    public Tiger(String name) {
        super(name, "Хищник, полосатый, большая кошка");
    }

    @Override
    public void sound() {
        System.out.println(name + " рычит: РРР-РРР!");
    }
}