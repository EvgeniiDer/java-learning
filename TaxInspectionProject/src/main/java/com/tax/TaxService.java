package com.tax;

import java.util.*;
public class TaxService {
    private Map<String, Person> database = new HashMap<>();

    public void printDataBase(){
        if(database.isEmpty()){
            System.out.println("Database is empty");
            return;
        }
        for(Map.Entry<String, Person> entry : database.entrySet()){
            System.out.println("ID: " + entry.getKey() + " -> " + entry.getValue());
        }
    }

}