package com.example.inheritance.devicetask4;


public class Steamboat extends Device{

    public Steamboat(String name, String description){
        super(name, description);
    }

    @Override
    public void Sound() {
        System.out.println("Sounds The steamship's whistle");
    }
}