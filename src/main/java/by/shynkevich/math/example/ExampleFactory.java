package by.shynkevich.math.example;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import by.shynkevich.math.example.domain.Operator;
import by.shynkevich.math.example.domain.term.OperatorTerm;
import by.shynkevich.math.example.domain.example.TypicalExample;
import by.shynkevich.math.example.domain.term.ValueTerm;

public class ExampleFactory {

    private static final Random RANDOM = new Random();
    private static final OperatorTerm EQUAL_TERM = new OperatorTerm(Operator.EQUALS, false);
    private static final OperatorTerm[] OPERATION_TERMS = Stream.of(Operator.MINUS, Operator.PLUS)
            .map(value -> new OperatorTerm(value, false)).toArray(OperatorTerm[]::new);

    public static TypicalExample createExample(int minLimit, int maxLimit) {
        OperatorTerm operation = OPERATION_TERMS[RANDOM.nextInt(OPERATION_TERMS.length)];
        return exampleStrategy(operation, minLimit, maxLimit);
    }

    private static TypicalExample exampleStrategy(OperatorTerm operation, int minLimit, int maxLimit) {
        int result;
        int firstTerm;
        int secondTerm;

        if (Operator.PLUS.equals(operation.getValue())) {
            result = getRandomNumberInRange(minLimit, maxLimit);
            firstTerm = getRandomNumberInRange(minLimit, result);
            secondTerm = result - firstTerm;
        } else {
            firstTerm = getRandomNumberInRange(minLimit, maxLimit);
            result = getRandomNumberInRange(minLimit, firstTerm);
            secondTerm = firstTerm - result;
        }
        ValueTerm[] terms = convertToTerms(firstTerm, secondTerm, result);
        return new TypicalExample(terms[0], operation, terms[1], EQUAL_TERM, terms[2]);
    }

    private static ValueTerm[] convertToTerms(int ...values) {
        int toHide = RANDOM.nextInt(values.length);
        return IntStream.range(0, values.length)
                .mapToObj(i -> new ValueTerm(values[i], toHide == i))
                .toArray(ValueTerm[]::new);
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
