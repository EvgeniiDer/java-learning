package com.example.inheritance.devicetask4;


public class Car extends Device{
    public Car(String name, String description) {
        super(name, description);
    }

    @Override
    public void Sound() {
        System.out.println("Sounds Car's engine sound faded");
    }

}
