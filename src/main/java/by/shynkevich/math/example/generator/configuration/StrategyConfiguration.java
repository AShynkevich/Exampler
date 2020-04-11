package by.shynkevich.math.example.generator.configuration;

import java.util.Map;

import by.shynkevich.math.example.domain.ExampleType;
import by.shynkevich.math.example.domain.Operator;
import by.shynkevich.math.example.generator.example.ExampleGenerator;
import by.shynkevich.math.example.generator.example.OneActionCompareGenerator;
import by.shynkevich.math.example.generator.example.OneActionGenerator;
import by.shynkevich.math.example.generator.action.Action;
import by.shynkevich.math.example.generator.action.SubtractionAction;
import by.shynkevich.math.example.generator.action.SumAction;
import com.google.common.collect.ImmutableMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StrategyConfiguration {

    @Bean
    public Map<Operator, Action> getActionStrategy() {
        return ImmutableMap.<Operator, Action>builder()
                .put(Operator.MINUS, new SubtractionAction())
                .put(Operator.PLUS, new SumAction())
                .build();
    }

    @Bean
    public Map<ExampleType, ExampleGenerator> getExampleStrategy() {
        return ImmutableMap.<ExampleType, ExampleGenerator>builder()
                .put(ExampleType.ONE_ACTION, getOneActionGenerator())
                .put(ExampleType.ONE_ACTION_COMPARING, getOneActionCompareGenerator())
                .build();
    }

    @Bean
    public ExampleGenerator getOneActionGenerator() {
        return new OneActionGenerator();
    }

    @Bean
    public ExampleGenerator getOneActionCompareGenerator() {
        return new OneActionCompareGenerator();
    }
}
