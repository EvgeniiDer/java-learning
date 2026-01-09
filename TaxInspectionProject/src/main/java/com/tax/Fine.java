package com.tax;

public class Fine{
    private String type;
    private String city;
    private double amount;


    public Fine(String type, String city, double amount){
        this.type = type;
        this.city = city;
        this.amount = amount;
    }
    public String getType(){
        return type;
    }
    public String getCity(){
        return city;
    }
    public double getAmount(){
        return amount;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setCity(String city){
        this.city = city;
    }
    public void setAmount(double amount)
    {
        this.amount = amount;
    }
    @Override
    public String toString()
    {
        return String.format("[Type %s, City %s, Summary %.2f]", type, city, amount);
    }
}
