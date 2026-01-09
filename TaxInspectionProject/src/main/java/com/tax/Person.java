package com.tax;
import java.util.ArrayList;
import java.util.List;

public class Person{
    public String name;
    public List<Fine> fines;

    public Person(String name){
        this.name = name;
        fines = new ArrayList<>();
    }
    public String getName(){return this.name;}
    public void setName(String name){this.name = name;}

    public List<Fine> getFines(){return this.fines;}
    public void setFines(Fine fines)
    {
        this.fines.add(fines);
    }
    @Override
    public String toString()
    {
        return "Name: " + this.name + " Fine: " + (fines.isEmpty() ? "Empty" : fines);
    }
}