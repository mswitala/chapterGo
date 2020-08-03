package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.security.MessageDigest;
import java.util.Random;

@SpringBootApplication
public class Application {

    @Bean
    public FileWriter fileWriter() {
        FileWriter fw = null;
        try {
            File file = new File("/app/results/javaData.txt");

            file.delete();
            file.createNewFile();

            fw = new FileWriter(file, true);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fw;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}