package by.shynkevich.math.example.util;

import java.util.Random;

import by.shynkevich.math.example.domain.Operator;

public class MathUtils {

    /**
     * Gets random number from range.
     *
     * @param min min limit bound
     * @param max max limit bound
     * @return generated random number
     */
    public static int getRandomNumberInRange(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    /**
     * Generates action by operator.
     *
     * @param operator the {@link Operator}.
     * @param minLimit min limit bound
     * @param maxLimit max limit bound
     * @return the array of terms
     */
    public static int[] generateAction(Operator operator, int minLimit, int maxLimit) {
        if (Operator.PLUS.equals(operator)) {
            return generateSumAction(minLimit, maxLimit);
        }
        return generateSubAction(minLimit, maxLimit);
    }

    /**
     * Generates sum action.
     *
     * @param minLimit min limit bound
     * @param maxLimit max limit bound
     * @return the array of terms
     */
    public static int[] generateSumAction(int minLimit, int maxLimit) {
        int result = MathUtils.getRandomNumberInRange(minLimit, maxLimit);
        int firstTerm = MathUtils.getRandomNumberInRange(minLimit, result);
        int secondTerm = result - firstTerm;

        return new int[]{firstTerm, secondTerm, result};
    }

    /**
     * Generates sub action.
     *
     * @param minLimit min min limit bound
     * @param maxLimit max min limit bound
     * @return the array of terms
     */
    public static int[] generateSubAction(int minLimit, int maxLimit) {
        int firstTerm = MathUtils.getRandomNumberInRange(minLimit, maxLimit);
        int result = MathUtils.getRandomNumberInRange(minLimit, firstTerm);
        int secondTerm = firstTerm - result;

        return new int[]{firstTerm, secondTerm, result};
    }
}
