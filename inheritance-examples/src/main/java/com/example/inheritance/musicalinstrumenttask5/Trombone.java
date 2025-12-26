package com.example.inheritance.musicalinstrumenttask5;

public class Trombone extends MusicalInstrument {

    public Trombone(String name, String description) {
        super(name, description, "Тромбон появился в XV веке. Его отличительная черта - кулиса, которая позволяет плавно изменять высоту звука.");
    }

    @Override
    public void Sound() {
        System.out.println("Звук: *Мощные и раскатистые басовые ноты духового инструмента*");
    }
}