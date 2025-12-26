package com.university.dictionary;

import com.university.dictionary.*;
import java.util.*;

public class Dictionary{
    private Map<String, Word> data = new HashMap<>();

    public void addOrReplaceWord(String word, String translation){
        data.put(word.toLowerCase(), new Word(translation));
    }
    public void removeWord(String word){
        data.remove(word.toLowerCase());
    }
    public void lookWord(String word){
       Word entry = data.get(word.toLowerCase());
       if(entry != null){
           entry.incrementCount();
           System.out.println("Найдено -> " + word + ": " + entry);
       }else{
           System.out.println("Ошибка: Слово '" + word + "' не найдено.");
       }
    }
    public void addTranslationToWord(String word, String translation){
        if(data.containsKey(word.toLowerCase())){
            data.get(word.toLowerCase()).addTranslation(translation);
        }else{
            System.out.println("Сначала создайте слово!");
        }
    }
    public void printTop10(boolean popular) {
        if (data.isEmpty()) {
            System.out.println("Словарь пуст.");
            return;
        }

        System.out.println("\n--- ТОП-10 " + (popular ? "ПОПУЛЯРНЫХ" : "НЕПОПУЛЯРНЫХ") + " ---");

        data.entrySet().stream()
                .sorted((e1, e2) -> popular
                        ? Integer.compare(e2.getValue().getAccessCount(), e1.getValue().getAccessCount())
                        : Integer.compare(e1.getValue().getAccessCount(), e2.getValue().getAccessCount()))
                .limit(10)
                .forEach(e -> System.out.println(e.getKey() + " (" + e.getValue().getAccessCount() + " вызовов)"));
    }

}