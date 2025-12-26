package com.example.inheritance.devicetask4;


public class Kettle extends Device{

    public Kettle(String name, String description){
        super(name, description);
    }

    @Override
    public void Sound() {
        System.out.println("Sounds the whistle of a boiling kettle");
    }
}