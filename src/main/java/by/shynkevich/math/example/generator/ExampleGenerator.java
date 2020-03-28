package by.shynkevich.math.example.generator;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import by.shynkevich.math.example.domain.Operator;
import by.shynkevich.math.example.domain.example.TypicalExample;
import by.shynkevich.math.example.domain.term.OperatorTerm;
import by.shynkevich.math.example.domain.term.ValueTerm;

public interface ExampleGenerator {

    Random RANDOM = new Random();

    TypicalExample generate(int minLimit, int maxLimit);

    default ValueTerm[] convertToTerms(int countToHide, int... values) {
        Set<Integer> indexes = RANDOM.ints(countToHide, 0, values.length).boxed().collect(Collectors.toSet());
        return IntStream.range(0, values.length)
                .mapToObj(i -> new ValueTerm(values[i], indexes.contains(i)))
                .toArray(ValueTerm[]::new);
    }

    default OperatorTerm convertToTerm(Operator operator, boolean hide) {
        return new OperatorTerm(operator, hide);
    }
}
