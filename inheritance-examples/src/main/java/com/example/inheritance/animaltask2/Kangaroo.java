package com.example.inheritance.animaltask2;


public class Kangaroo extends Animal {

    public Kangaroo(String name) {
        super(name, "Травоядное!! Сумчатое");
    }

    @Override
    public void sound() {
        System.out.println(name + " Вроде бы лает)))");
    }
}