package com.example.inheritance.musicalinstrumenttask5;
public class Ukulele extends MusicalInstrument {

    public Ukulele(String name, String description) {
        super(name, description, "Укулеле развилась на Гавайских островах в XIX веке на основе португальской гитары 'машете'.");
    }

    @Override
    public void Sound() {
        System.out.println("Звук: *Веселые и звонкие переборы нейлоновых струн*");
    }
}