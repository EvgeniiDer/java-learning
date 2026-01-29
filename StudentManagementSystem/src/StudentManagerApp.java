import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StudentManagerApp {

    // Настройки подключения к БД (SQLite создает файл students.db в корне проекта)
    private static final String DB_URL = "jdbc:sqlite:students.db";

    // Сканнер для ввода с клавиатуры
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // 1. При запуске сразу пробуем подключиться и создать таблицу, если её нет
        try {
            // Подгружаем класс драйвера (иногда нужно для старых версий Java)
            Class.forName("org.sqlite.JDBC");
            initDatabase();

            System.out.println("✅ Подключение к базе данных успешно!");
            System.out.println("Добро пожаловать в Систему Учета Студентов!");

            // 2. Бесконечный цикл меню
            while (true) {
                printMenu();
                int choice = getValidIntInput();

                switch (choice) {
                    case 1 -> createStudent();
                    case 2 -> readAllStudents();
                    case 3 -> updateStudentEmail();
                    case 4 -> deleteStudent();
                    case 0 -> {
                        System.out.println("Выход из программы...");
                        return;
                    }
                    default -> System.out.println("⚠️ Неверная команда, попробуйте снова.");
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Ошибка: Не найден драйвер JDBC! Добавьте sqlite-jdbc.jar в проект.");
        } catch (SQLException e) {
            System.err.println("❌ Ошибка базы данных: " + e.getMessage());
        }
    }

    // --- МЕНЮ И ИНТЕРФЕЙС ---

    private static void printMenu() {
        System.out.println("\n--- МЕНЮ ---");
        System.out.println("1. Добавить студента (Create)");
        System.out.println("2. Показать всех (Read)");
        System.out.println("3. Изменить Email студента (Update)");
        System.out.println("4. Удалить студента (Delete)");
        System.out.println("0. Выход");
        System.out.print("Выберите действие: ");
    }

    // --- РАБОТА С БАЗОЙ ДАННЫХ (JDBC) ---

    // Инициализация таблицы
    private static void initDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS students (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "email TEXT NOT NULL, " +
                    "score INTEGER)";
            stmt.execute(sql);
        }
    }

    // C - Create
    private static void createStudent() throws SQLException {
        System.out.print("Введите имя студента: ");
        String name = scanner.nextLine();

        // Валидация Email
        String email;
        while (true) {
            System.out.print("Введите Email: ");
            email = scanner.nextLine();
            if (email.contains("@") && email.contains(".")) {
                break;
            }
            System.out.println("❌ Некорректный email. Попробуйте снова.");
        }

        // Валидация Оценки
        System.out.print("Введите средний балл (0-100): ");
        int score = getValidIntInput();

        String sql = "INSERT INTO students(name, email, score) VALUES(?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, score);
            pstmt.executeUpdate();
            System.out.println("✅ Студент успешно добавлен!");
        }
    }

    // R - Read
    private static void readAllStudents() throws SQLException {
        String sql = "SELECT * FROM students";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\n--- Список студентов ---");
            System.out.printf("%-5s %-20s %-25s %-5s%n", "ID", "Имя", "Email", "Балл");
            System.out.println("----------------------------------------------------------");

            boolean hasData = false;
            while (rs.next()) {
                hasData = true;
                System.out.printf("%-5d %-20s %-25s %-5d%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getInt("score"));
            }

            if (!hasData) {
                System.out.println("Список пуст.");
            }
        }
    }

    // U - Update
    private static void updateStudentEmail() throws SQLException {
        System.out.print("Введите ID студента для изменения: ");
        int id = getValidIntInput();

        System.out.print("Введите новый Email: ");
        String newEmail = scanner.nextLine();

        String sql = "UPDATE students SET email = ? WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newEmail);
            pstmt.setInt(2, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Email обновлен!");
            } else {
                System.out.println("⚠️ Студент с таким ID не найден.");
            }
        }
    }

    // D - Delete
    private static void deleteStudent() throws SQLException {
        System.out.print("Введите ID студента для удаления: ");
        int id = getValidIntInput();

        String sql = "DELETE FROM students WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Студент удален из базы.");
            } else {
                System.out.println("⚠️ Студент с таким ID не найден.");
            }
        }
    }

    // --- ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ---

    // Безопасный ввод числа (чтобы программа не падала, если ввели буквы)
    private static int getValidIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("❌ Это не число! Попробуйте снова: ");
            }
        }
    }
}