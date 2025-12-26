package com.example.inheritance.humantask1;

public class Sailor extends Human{
    private String _profession;

    public Sailor(String name, int age){
        super(name, age);
        this._profession = "Sailor";
    }
    @Override
    public void printInfo()
    {
        System.out.println("Human: " + _name + ", age: " + _age + " profession: " + _profession);
    }
}
