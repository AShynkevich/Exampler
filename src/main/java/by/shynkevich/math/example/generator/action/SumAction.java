package by.shynkevich.math.example.generator.action;

import by.shynkevich.math.example.util.MathUtils;

/**
 * Generates sum action sequence.
 */
public class SumAction implements Action {

    @Override
    public int[] generate(int minLimit, int maxLimit) {
        int result = MathUtils.getRandomNumberInRange(minLimit, maxLimit);
        int firstTerm = MathUtils.getRandomNumberInRange(minLimit, result);
        int secondTerm = result - firstTerm;

        return new int[]{firstTerm, secondTerm, result};
    }
}
