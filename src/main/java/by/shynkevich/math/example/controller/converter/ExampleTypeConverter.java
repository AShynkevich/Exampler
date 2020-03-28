package by.shynkevich.math.example.controller.converter;

import java.beans.PropertyEditorSupport;

import by.shynkevich.math.example.domain.ExampleType;

public class ExampleTypeConverter extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String capitalized = text.toUpperCase();
        setValue(ExampleType.valueOf(capitalized));
    }
}
