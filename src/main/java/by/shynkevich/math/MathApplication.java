package by.shynkevich.math;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MathApplication {
    public static void main(String[] args) {
        SpringApplication.run(MathApplication.class, args);
        openHomePage();
    }

    private static void openHomePage() {
        try {
            System.setProperty("java.awt.headless", "false");
            URI homepage = new URI("http://localhost:8080/");
            Desktop.getDesktop().browse(homepage);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
