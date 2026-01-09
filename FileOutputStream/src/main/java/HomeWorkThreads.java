import java.io.*;
import java.math.BigInteger;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HomeWorkThreads {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- МЕНЮ ЗАДАНИЙ (Многопоточность) ---");
            System.out.println("1. Массив: сумма и среднее (Задание 1)");
            System.out.println("2. Файл: простые числа и факториал (Задание 2)");
            System.out.println("3. Копирование директории (Задание 3)");
            System.out.println("4. Поиск слов и цензура (Задание 4)");
            System.out.println("0. Выход");
            System.out.print("Выберите задание: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> runTask1();
                case "2" -> runTask2();
                case "3" -> runTask3();
                case "4" -> runTask4();
                case "0" -> {
                    System.out.println("Выход.");
                    return;
                }
                default -> System.out.println("Неверный ввод.");
            }
        }
    }

    static class ArrayData {
        int[] array;
        long sum;
        double average;
    }

    private static void runTask1() {
        System.out.println("\n--- Задание 1 ---");
        ArrayData data = new ArrayData();
        int size = 10;
        data.array = new int[size];

        // Поток 1: Заполнение
        Thread filler = new Thread(() -> {
            Random random = new Random();
            System.out.println("[Filler] Заполняю массив...");
            for (int i = 0; i < data.array.length; i++) {
                data.array[i] = random.nextInt(100);
            }
            System.out.println("[Filler] Массив заполнен: " + Arrays.toString(data.array));
        });

        Thread summer = new Thread(() -> {
            try {
                filler.join(); // Ждем заполнения
                System.out.println("[Summer] Считаю сумму...");
                long s = 0;
                for (int i : data.array) s += i;
                data.sum = s;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread averager = new Thread(() -> {
            try {
                filler.join(); // Ждем заполнения
                System.out.println("[Averager] Считаю среднее...");
                double s = 0;
                for (int i : data.array) s += i;
                data.average = s / data.array.length;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Запуск
        filler.start();
        summer.start();
        averager.start();

        try {
            summer.join();
            averager.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n--- Результаты в Main ---");
        System.out.println("Массив: " + Arrays.toString(data.array));
        System.out.println("Сумма: " + data.sum);
        System.out.println("Среднее: " + data.average);
    }

    // ====================== ЗАДАНИЕ 2 ======================
    private static void runTask2() {
        System.out.println("\n--- Задание 2 ---");
        System.out.print("Введите путь к файлу для генерации чисел (например, nums.txt): ");
        String pathStr = scanner.nextLine();
        File file = new File(pathStr);

        Thread filler = new Thread(() -> {
            try (PrintWriter pw = new PrintWriter(file)) {
                Random rnd = new Random();
                System.out.println("[Gen] Генерирую 20 чисел (0-20 для корректного факториала)...");
                for (int i = 0; i < 20; i++) {
                    pw.println(rnd.nextInt(21)); // числа 0-20
                }
                System.out.println("[Gen] Файл записан.");
            } catch (IOException e) {
                System.out.println("[Gen] Ошибка записи: " + e.getMessage());
            }
        });

        Thread primeFinder = new Thread(() -> {
            try {
                filler.join(); // Ждем
                List<Integer> primes = new ArrayList<>();
                try (Scanner sc = new Scanner(file)) {
                    while (sc.hasNextInt()) {
                        int num = sc.nextInt();
                        if (isPrime(num)) primes.add(num);
                    }
                }
                try (PrintWriter pw = new PrintWriter("primes.txt")) {
                    for (int p : primes) pw.println(p);
                }
                System.out.println("[Primes] Найдено простых чисел: " + primes.size() + ". Записано в primes.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread factorialCalc = new Thread(() -> {
            try {
                filler.join(); // Ждем
                List<String> facts = new ArrayList<>();
                try (Scanner sc = new Scanner(file)) {
                    while (sc.hasNextInt()) {
                        int num = sc.nextInt();
                        facts.add(num + "! = " + calculateFactorial(num));
                    }
                }
                try (PrintWriter pw = new PrintWriter("factorials.txt")) {
                    for (String f : facts) pw.println(f);
                }
                System.out.println("[Factorial] Посчитаны факториалы. Записано в factorials.txt");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        filler.start();
        primeFinder.start();
        factorialCalc.start();

        try {
            primeFinder.join();
            factorialCalc.join();
        } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Статистика: Операции завершены успешно.");
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    private static BigInteger calculateFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        for (int i = 2; i <= n; i++)
            result = result.multiply(BigInteger.valueOf(i));
        return result;
    }

    // ====================== ЗАДАНИЕ 3 ======================
    private static void runTask3() {
        System.out.println("\n--- Задание 3 ---");
        System.out.print("Введите путь исходной директории: ");
        String srcPath = scanner.nextLine();
        System.out.print("Введите путь новой директории: ");
        String destPath = scanner.nextLine();

        Path sourceDir = Paths.get(srcPath);
        Path targetDir = Paths.get(destPath);

        if (!Files.exists(sourceDir) || !Files.isDirectory(sourceDir)) {
            System.out.println("Исходная директория не существует.");
            return;
        }

        AtomicInteger fileCount = new AtomicInteger(0);
        AtomicInteger dirCount = new AtomicInteger(0);

        Thread copyThread = new Thread(() -> {
            try {
                Files.walkFileTree(sourceDir, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                        Path targetPath = targetDir.resolve(sourceDir.relativize(dir));
                        if (!Files.exists(targetPath)) {
                            Files.createDirectories(targetPath);
                            dirCount.incrementAndGet();
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        Path targetPath = targetDir.resolve(sourceDir.relativize(file));
                        Files.copy(file, targetPath, StandardCopyOption.REPLACE_EXISTING);
                        fileCount.incrementAndGet();
                        return FileVisitResult.CONTINUE;
                    }
                });
                System.out.println("Копирование завершено.");
            } catch (IOException e) {
                System.out.println("Ошибка копирования: " + e.getMessage());
            }
        });

        copyThread.start();
        try {
            copyThread.join();
        } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Статистика:");
        System.out.println("Скопировано папок: " + dirCount.get());
        System.out.println("Скопировано файлов: " + fileCount.get());
    }

    // ====================== ЗАДАНИЕ 4 ======================
    private static void runTask4() {
        System.out.println("\n--- Задание 4 ---");
        System.out.print("Введите путь к директории для поиска: ");
        String dirPath = scanner.nextLine();
        System.out.print("Введите слово для поиска: ");
        String searchWord = scanner.nextLine();

        System.out.print("Введите путь к файлу с запрещенными словами: ");
        String forbiddenWordsPath = scanner.nextLine();

        Path startPath = Paths.get(dirPath);
        if(!Files.isDirectory(startPath)) {
            System.out.println("Директория не найдена.");
            return;
        }

        String mergedFileName = "merged_result.txt";

        Thread searcher = new Thread(() -> {
            System.out.println("[Searcher] Начинаю поиск файлов с словом '" + searchWord + "'...");
            int foundCount = 0;
            try (PrintWriter pw = new PrintWriter(mergedFileName);
                 Stream<Path> stream = Files.walk(startPath)) {

                List<Path> files = stream
                        .filter(Files::isRegularFile)
                        .filter(p -> !p.getFileName().toString().equals(mergedFileName)) // исключаем сам файл результата
                        .collect(Collectors.toList());

                for (Path p : files) {
                    try {
                        String content = Files.readString(p);
                        if (content.contains(searchWord)) {
                            pw.println("----- FILE: " + p.getFileName() + " -----");
                            pw.println(content);
                            pw.println();
                            foundCount++;
                        }
                    } catch (IOException e) {
                    }
                }
                System.out.println("[Searcher] Найдено и слито файлов: " + foundCount);
            } catch (IOException e) {
                System.out.println("Ошибка поиска: " + e.getMessage());
            }
        });

        Thread censor = new Thread(() -> {
            try {
                searcher.join(); // Ждем окончания поиска
                System.out.println("[Censor] Начинаю вырезание запрещенных слов...");

                File banFile = new File(forbiddenWordsPath);
                List<String> banned = new ArrayList<>();
                if(banFile.exists()) {
                    try(Scanner sc = new Scanner(banFile)) {
                        while(sc.hasNext()) banned.add(sc.next());
                    }
                } else {
                    System.out.println("[Censor] Файл с запрещенными словами не найден, цензура пропущена.");
                    return;
                }

                if (banned.isEmpty()) {
                    System.out.println("[Censor] Список запрещенных слов пуст.");
                    return;
                }

                File mergedFile = new File(mergedFileName);
                if(!mergedFile.exists()) return;

                String content = Files.readString(mergedFile.toPath());

                int replaceCount = 0;
                for(String ban : banned) {
                    if(content.contains(ban)) {
                        content = content.replaceAll(ban, "***");
                        replaceCount++;
                    }
                }

                Files.writeString(mergedFile.toPath(), content);
                System.out.println("[Censor] Цензура завершена. Обработано (типов) слов: " + replaceCount);

            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
        });

        searcher.start();
        censor.start();

        try {
            censor.join();
        } catch (InterruptedException e) { e.printStackTrace(); }

        System.out.println("Операция завершена. Итоговый файл: " + mergedFileName);
    }
}