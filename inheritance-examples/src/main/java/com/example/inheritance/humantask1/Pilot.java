package com.example.inheritance.humantask1;


public class Pilot extends Human{
    private String _profession;

    public Pilot(String name, int age){
        super(name, age);
        this._profession = "Pilot";
    }
    @Override
    public void printInfo()
    {
        System.out.println("Human: " + _name + ", age: " + _age + " profession: " + _profession);
    }
}
