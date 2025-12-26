package com.example.inheritance.animaltask2;


public class Crocodile extends Animal {

    public Crocodile(String name) {
        super(name, "Хищник, рептилия");
    }

    @Override
    public void sound() {
        System.out.println(name + "Ревет)))");
    }
}