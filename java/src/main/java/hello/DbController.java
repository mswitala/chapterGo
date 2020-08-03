package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Random;

@RestController
public class DbController {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    FileWriter fileWriter;

    final static Random r = new Random();

    private static int min = 1;
    private static int max = 1000;

    @RequestMapping("/db")
    public String getRandomData() throws IOException {
        String randomDataFromDb = getRandomDataFromDb();
        saveToFile(randomDataFromDb);
        return "Java response: " + randomDataFromDb;
    }

    private String getRandomDataFromDb() {
        int i = r.nextInt(max - min + 1) + min;
        String query = "select user_agent from visitors where id = " + i;

        return jdbcTemplate
                .queryForObject(query, String.class);
    }

    private void saveToFile(String data) throws IOException {
        fileWriter.append(data + "\r\n");
        fileWriter.flush();
    }
}