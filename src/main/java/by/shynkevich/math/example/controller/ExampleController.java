package by.shynkevich.math.example.controller;

import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpSession;

import by.shynkevich.math.example.controller.converter.ExampleTypeConverter;
import by.shynkevich.math.example.domain.ExampleType;
import by.shynkevich.math.example.domain.example.TypicalExample;
import by.shynkevich.math.example.exception.NoServiceException;
import by.shynkevich.math.example.service.ExampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/examples")
public class ExampleController {

    private static final String SERVICE_KEY = "service";
    private static final String EXAMPLES_KEY = "examples";
    private static final String EXAMPLE_TABLE_VIEW = "exampleTable";

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(ExampleType.class, new ExampleTypeConverter());
    }

    @PostMapping
    public String main(@RequestParam(name = "count", defaultValue = "10") int count,
                       @RequestParam(name = "minLimit", defaultValue = "0") int minLimit,
                       @RequestParam(name = "maxLimit", defaultValue = "10") int maxLimit,
                       @RequestParam(name = "type", defaultValue = "ONE_ACTION") ExampleType type,
                       Model model, HttpSession session) {
        session.setAttribute(SERVICE_KEY, new ExampleService());
        ExampleService service = extractService(session);

        List<TypicalExample> examples = service.init(type, count, minLimit, maxLimit);
        model.addAttribute(EXAMPLES_KEY, examples);
        return EXAMPLE_TABLE_VIEW;
    }

    @GetMapping
    public ResponseEntity<Boolean> checkTotal(HttpSession session) {
        ExampleService service = extractService(session);
        return new ResponseEntity<>(service.allResolved(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Boolean> checkResult(@PathVariable("id") String id,
                                               @RequestParam("value") String value,
                                               HttpSession session) {
        boolean isSuccess = extractService(session).checkResult(id, value);
        return new ResponseEntity<>(isSuccess, HttpStatus.OK);
    }

    private ExampleService extractService(HttpSession session) {
        ExampleService service = (ExampleService) session.getAttribute(SERVICE_KEY);
        if (Objects.nonNull(service)) {
            return service;
        }
        throw new NoServiceException();
    }
}
