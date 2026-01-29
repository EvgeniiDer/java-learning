package server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;

public class SimpleWebServer {

    public static void main(String[] args) throws IOException {
        // 1. Создаем сервер, слушающий порт 8080
        // "0" — это размер очереди запросов (0 значит "системный дефолт")
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // 2. Создаем контекст "/"
        // Это значит, что любые запросы к серверу будет обрабатывать наш класс StaticHandler
        server.createContext("/", new StaticHandler());

        // 3. Запускаем сервер
        server.setExecutor(null); // использует дефолтный экзекьютор
        server.start();

        System.out.println("Сервер запущен! Открой в браузере: http://localhost:8080/text.txt");
        System.out.println("Сервер запущен! Открой в браузере: http://localhost:8080/cat.jpg");
        System.out.println("Сервер запущен! Открой в браузере: http://localhost:8080/logo.png");}

    // Класс-обработчик (Handler). Тут происходит вся магия.
    static class StaticHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {

            // А. Получаем путь к файлу из URL
            // Если запрос "http://localhost:8080/image.jpg", то URI будет "/image.jpg"
            String requestPath = exchange.getRequestURI().getPath();

            // Убираем первый слэш, чтобы путь был относительным (не "/image.jpg", а "image.jpg")
            // И указываем, что файлы лежат в папке "static"
            String filePath = "static" + requestPath;

            File file = new File(filePath);

            // Б. Проверяем, существует ли файл и является ли он файлом (а не папкой)
            if (file.exists() && file.isFile()) {

                // 1. Определяем MIME-тип (Content-Type)
                String contentType = getContentType(filePath);

                // 2. Устанавливаем заголовки ответа
                // "Content-Type" говорит браузеру, как отображать файл
                exchange.getResponseHeaders().set("Content-Type", contentType);

                // 3. Отправляем код 200 (OK) и размер файла
                exchange.sendResponseHeaders(200, file.length());

                // 4. Читаем файл в байты и отправляем в поток вывода
                // Важно! Для картинок используем именно байты, а не Writer
                try (OutputStream os = exchange.getResponseBody()) {
                    Files.copy(file.toPath(), os);
                }
                System.out.println("Отдан файл: " + filePath + " | Тип: " + contentType);

            } else {
                // В. Если файла нет — ошибка 404
                String response = "404 (Not Found)\nFile not found: " + filePath;
                exchange.sendResponseHeaders(404, response.length());

                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
                System.out.println("Ошибка 404: " + filePath);
            }
        }

        // Вспомогательный метод для определения типа файла
        private String getContentType(String path) {
            if (path.endsWith(".txt")) {
                return "text/plain";
            } else if (path.endsWith(".png")) {
                return "image/png";
            } else if (path.endsWith(".jpg") || path.endsWith(".jpeg")) {
                return "image/jpeg";
            } else {
                return "application/octet-stream"; // Тип для неизвестных файлов (браузер предложит скачать)
            }
        }
    }
}