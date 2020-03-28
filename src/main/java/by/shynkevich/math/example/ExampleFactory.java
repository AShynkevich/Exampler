package by.shynkevich.math.example;

import java.util.Map;

import by.shynkevich.math.example.domain.ExampleType;
import by.shynkevich.math.example.domain.example.TypicalExample;
import by.shynkevich.math.example.generator.ActionCompareGenerator;
import by.shynkevich.math.example.generator.ExampleGenerator;
import by.shynkevich.math.example.generator.OneActionGenerator;
import com.google.common.collect.ImmutableMap;

public class ExampleFactory {

    private static final Map<ExampleType, ExampleGenerator> EXAMPLE_STRATEGY = ImmutableMap.<ExampleType, ExampleGenerator>builder()
            .put(ExampleType.ONE_ACTION, new OneActionGenerator())
            .put(ExampleType.ONE_ACTION_COMPARING, new ActionCompareGenerator())
            .build();

    public static TypicalExample createExample(ExampleType type, int minLimit, int maxLimit) {
        ExampleGenerator generator = EXAMPLE_STRATEGY.get(type);
        return generator.generate(minLimit, maxLimit);
    }
}
