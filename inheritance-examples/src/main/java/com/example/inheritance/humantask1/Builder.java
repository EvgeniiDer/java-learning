package com.example.inheritance.humantask1;


public class Builder extends Human{
    private String _profession;

    public Builder(String name, int age){
        super(name, age);
        this._profession = "Builder";
    }
    @Override
    public void printInfo()
    {
        System.out.println("Human: " + _name + ", age: " + _age + " profession: " + _profession);
    }
}
