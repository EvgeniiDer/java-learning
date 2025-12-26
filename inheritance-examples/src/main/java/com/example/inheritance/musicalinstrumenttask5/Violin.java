package com.example.inheritance.musicalinstrumenttask5;

public class Violin extends MusicalInstrument {
    public Violin(String name, String description) {
        // Вызываем конструктор родителя и передаем ему всю информацию, включая историю.
        super(name, description, "Современный вид скрипки был создан в XVI веке в Италии. Считается 'королевой оркестра'.");
    }

    @Override
    public void Sound() {
        System.out.println("Звук: *Мелодичные и высокие звуки смычка по струнам*");
    }
}