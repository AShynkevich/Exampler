package by.shynkevich.math;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MathApplication {

    private static final String BROWSER_PROPERTY = "browser";
    private static final String LOCALHOST = "http://localhost:8080/";

    public static void main(String[] args) {
        SpringApplication.run(MathApplication.class, args);
        openHomePage();
    }

    private static void openHomePage() {
        if (Boolean.parseBoolean(System.getProperty(BROWSER_PROPERTY))) {
            try {
                System.setProperty("java.awt.headless", "false");
                URI homepage = new URI(LOCALHOST);
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(homepage);
            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
            }
        }
    }
}
