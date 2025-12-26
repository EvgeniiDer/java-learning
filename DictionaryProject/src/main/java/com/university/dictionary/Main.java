package com.university.dictionary;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Dictionary myDict = new Dictionary();
        Scanner input = new Scanner(System.in);

        myDict.addOrReplaceWord("apple", "яблоко");
        myDict.addOrReplaceWord("book", "книга");
        myDict.addOrReplaceWord("cat", "кот");

        while (true) {
            System.out.println("\n--- МЕНЮ СЛОВАРЯ ---");
            System.out.println("1. Найти перевод");
            System.out.println("2. Добавить/Заменить слово");
            System.out.println("3. Удалить слово");
            System.out.println("4. Добавить перевод к существующему слову");
            System.out.println("5. Топ-10 популярных");
            System.out.println("6. Топ-10 непопулярных");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = input.nextInt();
            input.nextLine(); // Очистка буфера

            switch (choice) {
                case 1:
                    System.out.print("Введите слово: ");
                    myDict.lookWord(input.nextLine());
                    break;
                case 2:
                    System.out.print("Введите слово: ");
                    String w = input.nextLine();
                    System.out.print("Введите перевод: ");
                    String t = input.nextLine();
                    myDict.addOrReplaceWord(w, t);
                    break;
                case 3:
                    System.out.print("Какое слово удалить?: ");
                    myDict.removeWord(input.nextLine());
                    break;
                case 4:
                    System.out.print("Введите слово: ");
                    String wordEx = input.nextLine();
                    System.out.print("Введите новый вариант перевода: ");
                    String transNew = input.nextLine();
                    myDict.addTranslationToWord(wordEx, transNew);
                    break;
                case 5:
                    myDict.printTop10(true);
                    break;
                case 6:
                    myDict.printTop10(false);
                    break;
                case 0:
                    System.out.println("Выход...");
                    return;
                default:
                    System.out.println("Неверный выбор.");
            }
        }
    }
}