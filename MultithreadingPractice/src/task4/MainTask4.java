package task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class SearchThread extends Thread {
    private String filePath;
    private String searchWord;
    private boolean found = false;
    private int count = 0;

    public SearchThread(String filePath, String searchWord) {
        this.filePath = filePath;
        this.searchWord = searchWord;
    }

    @Override
    public void run() {
        try (Scanner fileScanner = new Scanner(new File(filePath))) {
            while (fileScanner.hasNext()) {
                String word = fileScanner.next();
                if (word.equalsIgnoreCase(searchWord)) {
                    found = true;
                    count++;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка в потоке: Файл не найден");
        }
    }

    public boolean isFound() { return found; }
    public int getCount() { return count; }
}

public class MainTask4 {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();

        System.out.print("Введите слово для поиска: ");
        String word = scanner.nextLine();

        SearchThread searcher = new SearchThread(path, word);
        searcher.start();

        searcher.join();

        if (searcher.isFound()) {
            System.out.println("Слово найдено! Количество вхождений: " + searcher.getCount());
        } else {
            System.out.println("Слово не найдено.");
        }
    }
}