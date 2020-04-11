package by.shynkevich.math.example.generator.example;

import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;

import by.shynkevich.math.example.domain.ExampleType;
import by.shynkevich.math.example.domain.example.TypicalExample;
import by.shynkevich.math.example.exception.NoOperationException;
import org.springframework.stereotype.Component;

@Component
public class ExampleGeneratorFactory {

    private static final String NO_GENERATOR_FORMAT = "No generator for [%s] example type";

    @Resource
    private Map<ExampleType, ExampleGenerator> exampleStrategy;

    public TypicalExample createExample(ExampleType type, int minLimit, int maxLimit) {
        ExampleGenerator generator = exampleStrategy.get(type);
        if (Objects.isNull(generator)) {
            throw new NoOperationException(String.format(NO_GENERATOR_FORMAT, type.name()));
        }
        return generator.generate(minLimit, maxLimit);
    }
}
