package com.example.inheritance.devicetask4;


public class Microwave extends Device{
    public Microwave(String name, String description){
        super(name, description);
    }
    @Override
    public void Sound() {
        System.out.println("Sound: Microwave's end of work signal");
    }
}