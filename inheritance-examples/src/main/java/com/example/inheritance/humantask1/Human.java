package com.example.inheritance.humantask1;


public class Human
{
    protected String _name;
    protected int _age;

    public Human(String name, int age)
    {
        this._name = name;
        this._age = age;
    }
    public void printInfo()
    {
        System.out.println("Human: " + _name + ", age: " + _age);
    }
}