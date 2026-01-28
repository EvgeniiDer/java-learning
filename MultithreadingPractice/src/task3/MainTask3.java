package task3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class EvenWriterThread extends Thread {
    private List<Integer> numbers;
    private int count = 0;

    public EvenWriterThread(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("even.txt"))) {
            for (Integer num : numbers) {
                if (num % 2 == 0) {
                    writer.println(num);
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCount() { return count; }
}

class OddWriterThread extends Thread {
    private List<Integer> numbers;
    private int count = 0;

    public OddWriterThread(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public void run() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("odd.txt"))) {
            for (Integer num : numbers) {
                if (num % 2 != 0) {
                    writer.println(num);
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCount() { return count; }
}

public class MainTask3 {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите путь к файлу с числами: ");
        String path = scanner.nextLine();

        List<Integer> numbers = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File(path))) {
            while (fileScanner.hasNextInt()) {
                numbers.add(fileScanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!");
            return;
        }

        EvenWriterThread evenThread = new EvenWriterThread(numbers);
        OddWriterThread oddThread = new OddWriterThread(numbers);

        evenThread.start();
        oddThread.start();

        evenThread.join();
        oddThread.join();

        System.out.println("Количество четных (записано в even.txt): " + evenThread.getCount());
        System.out.println("Количество нечетных (записано в odd.txt): " + oddThread.getCount());
    }
}