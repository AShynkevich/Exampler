package by.shynkevich.math.example;

import java.util.Random;

import by.shynkevich.math.example.domain.Operation;
import by.shynkevich.math.example.domain.TypicalExample;

public class ExampleFactory {

    private static final Random RANDOM = new Random();

    public static TypicalExample createExample(int minLimit, int maxLimit) {
        Operation[] operations = Operation.values();
        Operation operation = operations[RANDOM.nextInt(2)];

        return exampleStrategy(operation, minLimit, maxLimit);
    }

    private static TypicalExample exampleStrategy(Operation operation, int minLimit, int maxLimit) {
        int result;
        int firstTerm;
        int secondTerm;

        if (Operation.PLUS.equals(operation)) {
            result = getRandomNumberInRange(minLimit, maxLimit);
            firstTerm = getRandomNumberInRange(minLimit, result);
            secondTerm = result - firstTerm;
        } else {
            firstTerm = getRandomNumberInRange(minLimit, maxLimit);
            result = getRandomNumberInRange(minLimit, firstTerm);
            secondTerm = firstTerm - result;
        }
        return new TypicalExample(operation, RANDOM.nextInt(3), firstTerm, secondTerm, result);
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
