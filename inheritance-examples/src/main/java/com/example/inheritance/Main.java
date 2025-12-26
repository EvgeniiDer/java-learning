package com.example.inheritance;
import com.example.inheritance.humantask1.*;
import com.example.inheritance.animaltask2.*;
import com.example.inheritance.moneytask3.*;
import com.example.inheritance.devicetask4.*;
import com.example.inheritance.musicalinstrumenttask5.*;

public class Main
{
    public static void main(String[] argc)
    {
        System.out.println("Task 1 Human");
        Human human = new Pilot("Evgenii", 33);
        human.printInfo();

        System.out.println("Task 2 Animal");
        Animal tiger = new Tiger("Tiger");
        tiger.showInfo();

        System.out.println("Task 3 Money");

        Money initialPrice = new Money(199, (byte) 90); // 199 рублей 90 копеек

        Product laptop = new Product("Ноутбук 'JavaMaster X1'", initialPrice);

        System.out.println("--- Информация о товаре до скидки ---");
        laptop.displayProductInfo();
        System.out.println("\n=====================================\n");

        Money discount = new Money(15, (byte) 50); // Скидка 15 рублей 50 копеек
        System.out.print("Применяем скидку в размере: ");
        discount.displayAmount();

        laptop.decreasePrice(discount);

        System.out.println("\n--- Информация о товаре после скидки ---");
        laptop.displayProductInfo();

        System.out.println("\n=====================================\n");

        Money bigDiscount = new Money(200, (byte)0);
        System.out.print("Попытка применить слишком большую скидку: ");
        bigDiscount.displayAmount();

        laptop.decreasePrice(bigDiscount);

        System.out.println("\n--- Информация о товаре после некорректной скидки ---");
        laptop.displayProductInfo();


        System.out.println("Task 4 Devices");
        Device[] devices = {
                new Kettle("Чайник Vitek", "Электрический чайник, объем 1.7л"),
                new Microwave("Микроволновка Samsung", "Мощность 800 Вт, с грилем"),
                new Car("Автомобиль Lada Granta", "Седан, двигатель 1.6л, цвет баклажан"),
                new Steamboat("Пароход 'Ласточка'", "Речной круизный двухпалубный пароход")
        };

        for (Device device : devices) {
            device.Show();
            device.Desc();
            device.Sound();
            System.out.println("----------------------------------------");
        }
        System.out.println("Task 4 MusicalInstrument");
        MusicalInstrument[] orchestra = {
                new Violin("Скрипка 'Il Cremonese'", "Струнный смычковый инструмент высокого регистра"),
                new Trombone("Тромбон 'Bach 42BO'", "Медный духовой инструмент с кулисой"),
                new Ukulele("Укулеле 'Kala Makala'", "Четырехструнная гавайская гитара"),
                new Cello("Виолончель 'Montagnana'", "Струнный смычковый инструмент басового регистра")
        };

        for (MusicalInstrument instrument : orchestra) {
            instrument.Show();
            instrument.Desc();
            instrument.Sound();
            instrument.History();
            System.out.println("\n==============================================\n");
        }
    }
}

