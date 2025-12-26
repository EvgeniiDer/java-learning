package com.example.inheritance.devicetask4;


public abstract class Device
{
    protected String name;
    protected String description;

    public Device(String name, String description){
        this.name = name;
        this.description = description;
    }
    public abstract void Sound();

    public void Show(){
        System.out.println("Device's Name: " + this.name);
    }
    public void Desc(){
        System.out.println("Description: " + this.description);
    }

}