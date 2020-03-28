package by.shynkevich.math.example.controller;

import by.shynkevich.math.example.exception.NoServiceException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(NoServiceException.class)
    public String handle() {
        return "welcome";
    }
}
