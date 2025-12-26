package com.example.inheritance.musicalinstrumenttask5;

public abstract class MusicalInstrument {
    protected String name;
    protected String description;
    protected String history;

    public MusicalInstrument(String name, String description, String history) {
        this.name = name;
        this.description = description;
        this.history = history;
    }

    public abstract void Sound();

    public void Show() {
        System.out.println("Инструмент: " + this.name);
    }

    public void Desc() {
        System.out.println("Описание: " + this.description);
    }

    public void History() {
        System.out.println("История: " + this.history);
    }
}