package com.example.inheritance.musicalinstrumenttask5;

public class Cello extends MusicalInstrument {

    public Cello(String name, String description) {
        super(name, description, "Виолончель появилась в начале XVI века. По тембру она близка к человеческому голосу (баритону).");
    }

    @Override
    public void Sound() {
        System.out.println("Звук: *Глубокий, бархатный и насыщенный звук струн*");
    }
}