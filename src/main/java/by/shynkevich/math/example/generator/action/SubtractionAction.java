package by.shynkevich.math.example.generator.action;

import by.shynkevich.math.example.util.MathUtils;

/**
 * Generates Subtraction action sequence.
 */
public class SubtractionAction implements Action {

    @Override
    public int[] generate(int minLimit, int maxLimit) {
        int firstTerm = MathUtils.getRandomNumberInRange(minLimit, maxLimit);
        int result = MathUtils.getRandomNumberInRange(minLimit, firstTerm);
        int secondTerm = firstTerm - result;

        return new int[]{firstTerm, secondTerm, result};
    }
}
