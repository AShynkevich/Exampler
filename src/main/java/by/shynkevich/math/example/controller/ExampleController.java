package by.shynkevich.math.example.controller;

import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import by.shynkevich.math.example.controller.converter.ExampleTypeConverter;
import by.shynkevich.math.example.domain.ExampleType;
import by.shynkevich.math.example.domain.Result;
import by.shynkevich.math.example.exception.NoServiceException;
import by.shynkevich.math.example.service.ExampleService;
import by.shynkevich.math.example.service.ExampleServiceFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    private static final String EXAMPLE_TABLE_VIEW = "exampleTable";
    private static final String REDIRECT_EXAMPLES = "redirect:/examples";

    @Resource
    private ExampleServiceFactory exServiceFactory;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(ExampleType.class, new ExampleTypeConverter());
    }

    @GetMapping
    public String continueExamples(HttpSession session) {
        if (Objects.isNull(session.getAttribute(SERVICE_KEY))) {
            return "redirect:/";
        }

        return EXAMPLE_TABLE_VIEW;
    }

    @PostMapping
    public String main(@RequestParam(name = "count", defaultValue = "10") int count,
                       @RequestParam(name = "minLimit", defaultValue = "0") int minLimit,
                       @RequestParam(name = "maxLimit", defaultValue = "10") int maxLimit,
                       @RequestParam(name = "train", defaultValue = "false") boolean isTrain,
                       @RequestParam(name = "type", defaultValue = "ONE_ACTION") ExampleType type,
                       HttpSession session) {
        ExampleService service = exServiceFactory.createService();
        service.init(new ExampleService.InitParamsBuilder()
                .withType(type)
                .withCount(count)
                .withMinLimit(minLimit)
                .withMaxLimit(maxLimit)
                .withTrain(isTrain)
                .build());

        session.setAttribute(SERVICE_KEY, service);
        return REDIRECT_EXAMPLES;
    }

    @GetMapping("/results")
    public ResponseEntity<Result> checkTotal(HttpSession session) {
        ExampleService service = extractService(session);
        return new ResponseEntity<>(service.getResult(), HttpStatus.OK);
    }

    @GetMapping("/restart")
    public String restartExamples(HttpSession session) {
        ExampleService service = extractService(session);
        service.init();
        return REDIRECT_EXAMPLES;
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
