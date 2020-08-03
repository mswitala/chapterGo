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

@RestController
public class ShaController {

    private static final String FILE_PATH = "/app/results/dataXXX.txt";

    @RequestMapping("/sh")
    public String getChecksum() throws Exception {
        MessageDigest digestAlg = MessageDigest.getInstance("SHA-256");
        String checksum = checksum(FILE_PATH, digestAlg);

        return "Java response: " + checksum;
    }

    private static String checksum(String filepath, MessageDigest md) throws Exception {

        // DigestInputStream is better, but you also can hash file like this.
        try (InputStream fis = new FileInputStream(filepath)) {
            byte[] buffer = new byte[1024];
            int nread;
            while ((nread = fis.read(buffer)) != -1) {
                md.update(buffer, 0, nread);
            }
        }

        // bytes to hex
        StringBuilder result = new StringBuilder();
        for (byte b : md.digest()) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}