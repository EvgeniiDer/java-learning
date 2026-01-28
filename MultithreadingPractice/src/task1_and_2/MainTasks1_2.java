package task1_and_2;
import java.util.Scanner;

class MaxThread extends Thread {
    private int[] array;
    private int max;

    public MaxThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        max = array[0];
        for (int i : array) {
            if (i > max) max = i;
        }
    }

    public int getMax() { return max; }
}

class MinThread extends Thread {
    private int[] array;
    private int min;

    public MinThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        min = array[0];
        for (int i : array) {
            if (i < min) min = i;
        }
    }

    public int getMin() { return min; }
}

// Класс для суммы
class SumThread extends Thread {
    private int[] array;
    private long sum;

    public SumThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        sum = 0;
        for (int i : array) sum += i;
    }

    public long getSum() { return sum; }
}

// Класс для среднего арифметического
class AvgThread extends Thread {
    private int[] array;
    private double avg;

    public AvgThread(int[] array) {
        this.array = array;
    }

    @Override
    public void run() {
        long sum = 0;
        for (int i : array) sum += i;
        avg = (double) sum / array.length;
    }

    public double getAvg() { return avg; }
}

public class MainTasks1_2 {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);

        // 1. Ввод массива
        System.out.print("Введите размер массива: ");
        int size = scanner.nextInt();
        int[] array = new int[size];
        System.out.println("Введите элементы массива:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        // --- ЗАДАНИЕ 1 ---
        MaxThread maxThread = new MaxThread(array);
        MinThread minThread = new MinThread(array);

        maxThread.start();
        minThread.start();

        // Ждем завершения потоков
        maxThread.join();
        minThread.join();

        System.out.println("--- Результаты Задания 1 ---");
        System.out.println("Максимум: " + maxThread.getMax());
        System.out.println("Минимум: " + minThread.getMin());

        // --- ЗАДАНИЕ 2 ---
        SumThread sumThread = new SumThread(array);
        AvgThread avgThread = new AvgThread(array);

        sumThread.start();
        avgThread.start();

        sumThread.join();
        avgThread.join();

        System.out.println("--- Результаты Задания 2 ---");
        System.out.println("Сумма: " + sumThread.getSum());
        System.out.println("Среднее: " + avgThread.getAvg());
    }
}

