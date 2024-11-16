package ru.netology.springbootdemo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    // Контейнеры для devapp и prodapp
    private static final GenericContainer<?> devAppContainer =
            new GenericContainer<>("devapp:latest").withExposedPorts(8080);
    private static final GenericContainer<?> prodAppContainer =
            new GenericContainer<>("prodapp:latest").withExposedPorts(8081);

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeAll
    public static void setUp() {
        // Запуск контейнеров
        devAppContainer.start();
        prodAppContainer.start();
    }

    @Test
    void testDevAppResponse() {
        // Получаем порт devapp
        Integer devAppPort = devAppContainer.getMappedPort(8080);
        // Делаем запрос к devapp через TestRestTemplate
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + devAppPort, String.class);
        // Проверяем ответ
        assertEquals("Expected response from devapp", response.getBody());
    }

    @Test
    void testProdAppResponse() {
        // Получаем порт prodapp
        Integer prodAppPort = prodAppContainer.getMappedPort(8081);
        // Делаем запрос к prodapp через TestRestTemplate
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + prodAppPort, String.class);
        // Проверяем ответ
        assertEquals("Expected response from prodapp", response.getBody());
    }
}