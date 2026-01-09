import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class HomeWorkContainer {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- МЕНЮ ЗАДАНИЙ ---");
            System.out.println("1. Сравнение двух файлов (Задание 1)");
            System.out.println("2. Самая длинная строка в файле (Задание 2)");
            System.out.println("3. Статистика массивов из файла (Задание 3)");
            System.out.println("4. Запись вариаций массива в файл (Задание 4)");
            System.out.println("5. ИС 'Корпорация' (Задание 5)");
            System.out.println("0. Выход");
            System.out.print("Выберите номер задания: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> runTask1();
                case "2" -> runTask2();
                case "3" -> runTask3();
                case "4" -> runTask4();
                case "5" -> runTask5();
                case "0" -> {
                    System.out.println("Выход из программы.");
                    return;
                }
                default -> System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    // ================= ЗАДАНИЕ 1 =================
    private static void runTask1() {
        System.out.println("\n--- ЗАДАНИЕ 1: Сравнение файлов ---");
        System.out.print("Введите путь к первому файлу: ");
        String path1 = scanner.nextLine();
        System.out.print("Введите путь ко второму файлу: ");
        String path2 = scanner.nextLine();

        try (BufferedReader br1 = new BufferedReader(new FileReader(path1));
             BufferedReader br2 = new BufferedReader(new FileReader(path2))) {

            String line1 = br1.readLine();
            String line2 = br2.readLine();
            int lineNumber = 1;
            boolean match = true;

            while (line1 != null || line2 != null) {
                if (line1 == null || line2 == null || !line1.equals(line2)) {
                    match = false;
                    System.out.println("Несовпадение в строке " + lineNumber + ":");
                    System.out.println("Файл 1: " + (line1 == null ? "<Конец файла>" : line1));
                    System.out.println("Файл 2: " + (line2 == null ? "<Конец файла>" : line2));
                }
                line1 = br1.readLine();
                line2 = br2.readLine();
                lineNumber++;
            }

            if (match) {
                System.out.println("Файлы идентичны.");
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файлов: " + e.getMessage());
        }
    }

    // ================= ЗАДАНИЕ 2 =================
    private static void runTask2() {
        System.out.println("\n--- ЗАДАНИЕ 2: Самая длинная строка ---");
        System.out.print("Введите путь к файлу: ");
        String path = scanner.nextLine();

        String longestLine = "";
        int maxLength = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.length() > maxLength) {
                    maxLength = line.length();
                    longestLine = line;
                }
            }
            System.out.println("Длина самой длинной строки: " + maxLength);
            System.out.println("Сама строка: " + longestLine);

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        }
    }

    // ================= ЗАДАНИЕ 3 =================
    private static void runTask3() {
        System.out.println("\n--- ЗАДАНИЕ 3: Массивы из файла ---");
        // Предполагается файл формата:
        // 1 2 3
        // 4 5 6
        System.out.print("Введите путь к файлу с массивами: ");
        String path = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            List<int[]> allArrays = new ArrayList<>();
            String line;

            // Чтение
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.trim().split("\\s+");
                int[] arr = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();
                allArrays.add(arr);
            }

            if (allArrays.isEmpty()) {
                System.out.println("Файл пуст или не содержит данных.");
                return;
            }

            int globalMax = Integer.MIN_VALUE;
            int globalMin = Integer.MAX_VALUE;
            long globalSum = 0;

            // Обработка
            for (int i = 0; i < allArrays.size(); i++) {
                int[] arr = allArrays.get(i);
                int localMax = Arrays.stream(arr).max().orElse(Integer.MIN_VALUE);
                int localMin = Arrays.stream(arr).min().orElse(Integer.MAX_VALUE);
                int localSum = Arrays.stream(arr).sum();

                // Глобальные
                if (localMax > globalMax) globalMax = localMax;
                if (localMin < globalMin) globalMin = localMin;
                globalSum += localSum;

                System.out.println("Массив " + (i + 1) + ": " + Arrays.toString(arr));
                System.out.println("  Макс: " + localMax + ", Мин: " + localMin + ", Сумма: " + localSum);
            }

            System.out.println("--- Общая статистика ---");
            System.out.println("Глобальный максимум: " + globalMax);
            System.out.println("Глобальный минимум: " + globalMin);
            System.out.println("Общая сумма: " + globalSum);

        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    // ================= ЗАДАНИЕ 4 =================
    private static void runTask4() {
        System.out.println("\n--- ЗАДАНИЕ 4: Запись массива в файл ---");
        System.out.print("Введите путь к файлу для сохранения: ");
        String path = scanner.nextLine();

        System.out.print("Введите элементы массива через пробел: ");
        String input = scanner.nextLine();
        String[] parts = input.trim().split("\\s+");

        try {
            int[] originalArr = Arrays.stream(parts).mapToInt(Integer::parseInt).toArray();

            // 1. Исходный
            String line1 = Arrays.toString(originalArr).replaceAll("[\\[\\],]", "");

            // 2. Четные
            String line2 = Arrays.stream(originalArr)
                    .filter(x -> x % 2 == 0)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));

            // 3. Нечетные
            String line3 = Arrays.stream(originalArr)
                    .filter(x -> x % 2 != 0)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));

            // 4. Перевернутый
            int[] reversedArr = new int[originalArr.length];
            for(int i=0; i<originalArr.length; i++) {
                reversedArr[i] = originalArr[originalArr.length - 1 - i];
            }
            String line4 = Arrays.toString(reversedArr).replaceAll("[\\[\\],]", "");

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                bw.write(line1); bw.newLine();
                bw.write(line2); bw.newLine();
                bw.write(line3); bw.newLine();
                bw.write(line4); bw.newLine();
                System.out.println("Данные успешно записаны в файл.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Ошибка ввода чисел.");
        } catch (IOException e) {
            System.out.println("Ошибка записи файла: " + e.getMessage());
        }
    }

    // ================= ЗАДАНИЕ 5 (КОРПОРАЦИЯ) =================

    // Внутренний класс сотрудника
    static class Employee {
        String firstName;
        String lastName;
        int age;

        public Employee(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        @Override
        public String toString() {
            return String.format("%s %s, возраст: %d", firstName, lastName, age);
        }

        // Формат для файла: Имя,Фамилия,Возраст
        public String toFileString() {
            return firstName + "," + lastName + "," + age;
        }
    }

    private static void runTask5() {
        System.out.println("\n--- ЗАДАНИЕ 5: ИС 'Корпорация' ---");
        List<Employee> employees = new ArrayList<>();

        System.out.print("Введите путь к файлу базы данных сотрудников (для загрузки): ");
        String dbPath = scanner.nextLine();

        // Загрузка при старте
        File file = new File(dbPath);
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 3) {
                        employees.add(new Employee(data[0], data[1], Integer.parseInt(data[2])));
                    }
                }
                System.out.println("Загружено сотрудников: " + employees.size());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Ошибка загрузки: " + e.getMessage());
            }
        } else {
            System.out.println("Файл не найден. Будет создан новый список.");
        }

        boolean running = true;
        while (running) {
            System.out.println("\n--- Меню Корпорации ---");
            System.out.println("1. Добавить сотрудника");
            System.out.println("2. Редактировать сотрудника");
            System.out.println("3. Удалить сотрудника");
            System.out.println("4. Поиск по фамилии");
            System.out.println("5. Вывод по критерию (возраст или первая буква)");
            System.out.println("6. Сохранить список в файл");
            System.out.println("0. Вернуться в главное меню");
            System.out.print("Ваш выбор: ");
            String subChoice = scanner.nextLine();

            switch (subChoice) {
                case "1" -> {
                    System.out.print("Имя: "); String fn = scanner.nextLine();
                    System.out.print("Фамилия: "); String ln = scanner.nextLine();
                    System.out.print("Возраст: "); int ag = Integer.parseInt(scanner.nextLine());
                    employees.add(new Employee(fn, ln, ag));
                    System.out.println("Сотрудник добавлен.");
                }
                case "2" -> {
                    System.out.print("Введите фамилию сотрудника для редактирования: ");
                    String searchLast = scanner.nextLine();
                    boolean found = false;
                    for (Employee e : employees) {
                        if (e.lastName.equalsIgnoreCase(searchLast)) {
                            System.out.println("Наден: " + e);
                            System.out.print("Новое имя (Enter, чтобы оставить): ");
                            String nFn = scanner.nextLine();
                            if(!nFn.isEmpty()) e.firstName = nFn;

                            System.out.print("Новая фамилия (Enter, чтобы оставить): ");
                            String nLn = scanner.nextLine();
                            if(!nLn.isEmpty()) e.lastName = nLn;

                            System.out.print("Новый возраст (0 или Enter, чтобы оставить): ");
                            String nAgStr = scanner.nextLine();
                            if(!nAgStr.isEmpty() && !nAgStr.equals("0")) e.age = Integer.parseInt(nAgStr);

                            found = true;
                            System.out.println("Обновлено.");
                        }
                    }
                    if(!found) System.out.println("Сотрудник не найден.");
                }
                case "3" -> {
                    System.out.print("Введите фамилию для удаления: ");
                    String delLast = scanner.nextLine();
                    employees.removeIf(e -> e.lastName.equalsIgnoreCase(delLast));
                    System.out.println("Операция удаления завершена.");
                }
                case "4" -> {
                    System.out.print("Фамилия для поиска: ");
                    String sLast = scanner.nextLine();
                    List<Employee> foundList = new ArrayList<>();
                    for(Employee e : employees) {
                        if(e.lastName.equalsIgnoreCase(sLast)) {
                            System.out.println(e);
                            foundList.add(e);
                        }
                    }
                    askToSaveResult(foundList);
                }
                case "5" -> {
                    System.out.println("a. По возрасту");
                    System.out.println("b. По первой букве фамилии");
                    String crit = scanner.nextLine();
                    List<Employee> resultList = new ArrayList<>();

                    if(crit.equals("a")) {
                        System.out.print("Введите возраст: ");
                        int fAge = Integer.parseInt(scanner.nextLine());
                        for(Employee e : employees) {
                            if(e.age == fAge) resultList.add(e);
                        }
                    } else if (crit.equals("b")) {
                        System.out.print("Введите букву: ");
                        String letter = scanner.nextLine().substring(0, 1);
                        for(Employee e : employees) {
                            if(e.lastName.toLowerCase().startsWith(letter.toLowerCase())) resultList.add(e);
                        }
                    }

                    if(resultList.isEmpty()) System.out.println("Ничего не найдено.");
                    else {
                        resultList.forEach(System.out::println);
                        askToSaveResult(resultList);
                    }
                }
                case "6" -> saveEmployees(employees, dbPath);
                case "0" -> {
                    saveEmployees(employees, dbPath); // Автосохранение при выходе
                    running = false;
                }
            }
        }
    }

    private static void saveEmployees(List<Employee> employees, String path) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Employee e : employees) {
                bw.write(e.toFileString());
                bw.newLine();
            }
            System.out.println("База данных сохранена в " + path);
        } catch (IOException e) {
            System.out.println("Ошибка сохранения: " + e.getMessage());
        }
    }

    private static void askToSaveResult(List<Employee> list) {
        if (list.isEmpty()) return;
        System.out.print("Сохранить найденные результаты в файл? (да/нет): ");
        String ans = scanner.nextLine();
        if (ans.equalsIgnoreCase("да")) {
            System.out.print("Введите имя файла для экспорта: ");
            String exportPath = scanner.nextLine();
            saveEmployees(list, exportPath);
        }
    }
}