package com.university.dictionary;
import java.util.*;
import java.util.stream.Collectors;

public class Word{
    private Set<String> translation;
    private int accessCount;

    public Word(String translation) {
        this.translation = new HashSet<>();
        this.translation.add(translation);
        this.accessCount = 0;
    }
    public void addTranslation(String t){
        this.translation.add(t);
    }
    public void removeTranslation(String t){
        this.translation.remove(t);
    }
    public void replaceTranslation(List<String> newTranslation)
    {
        this.translation.clear();
        this.translation.addAll(newTranslation);
    }
    public void incrementCount(){
        accessCount++;
    }
    public int getAccessCount(){
       return accessCount;
    }
    public Set<String> getTranslation(){
        return this.translation;
    }

    @Override
    public String toString()
    {
        return "Переводы: " + translation + " | Популярность: " + accessCount;
    }
}