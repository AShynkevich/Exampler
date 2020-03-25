package by.shynkevich.math;

import java.util.Objects;
import javax.servlet.http.HttpSession;

import by.shynkevich.math.example.service.ExampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WelcomeController {

    private static final String SERVICE_KEY = "service";
    private static final String WELCOME_VIEW = "welcome";
    private static final String EXAMPLE_TABLE_VIEW = "exampleTable";

    @GetMapping("/")
    public String main() {
        return WELCOME_VIEW;
    }

    @GetMapping("/examples")
    public String main(
            @RequestParam(name = "count", defaultValue = "10") int count,
            @RequestParam(name = "minLimit", defaultValue = "0") int minLimit,
            @RequestParam(name = "maxLimit", defaultValue = "10") int maxLimit,
            Model model, HttpSession session) {

        ExampleService service = extractService(session);
        service.init(count, minLimit, maxLimit);
        model.addAttribute("examples", service.getExamples());
        return EXAMPLE_TABLE_VIEW;
    }

    @PostMapping("/examples/{id}")
    public ResponseEntity<Boolean> checkResult(
            @PathVariable("id") String id,
            @RequestParam("value") int value,
            HttpSession session) {
        return new ResponseEntity<>(extractService(session).checkResult(id, value), HttpStatus.OK);
    }

    private ExampleService extractService(HttpSession session) {
        ExampleService service = (ExampleService) session.getAttribute(SERVICE_KEY);
        if (Objects.isNull(service)) {
            service = new ExampleService();
            session.setAttribute(SERVICE_KEY, service);
        }
        return service;
    }
}
