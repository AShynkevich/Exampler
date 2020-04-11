package by.shynkevich.math.example.generator.action;

import java.util.Optional;

import by.shynkevich.math.example.util.MathUtils;

/**
 * Generates Subtraction action sequence.
 */
public class SubtractionAction implements Action {

    @Override
    public int[] generate(int minLimit, int maxLimit, Integer forcedResult) {
        Optional<Integer> forcedResultOptional = Optional.ofNullable(forcedResult);
        int newMinLimit = forcedResultOptional.orElse(minLimit);

        int firstTerm = MathUtils.getRandomNumberInRange(newMinLimit, maxLimit);
        int result = forcedResultOptional.orElse(MathUtils.getRandomNumberInRange(newMinLimit, firstTerm));
        int secondTerm = firstTerm - result;

        return new int[]{firstTerm, secondTerm, result};
    }
}
