package by.shynkevich.math.example.generator.action;

/**
 * Action generator interface.
 */
public interface Action {

    /**
     * Generates sequence of terms what represent one math action.
     * <p>
     * e.g.
     * 1) statement: 4 + 5 = 9
     * it will produce the sequence: new int[]{4, 5, 9}
     * <p>
     * 2) statement: 9 - 5 = 4
     * it will produce the sequence: new int[]{9, 5, 4}
     * <p>
     * etc.
     * </p>
     *
     * @param minLimit min limit bound
     * @param maxLimit max limit bound
     * @return the array of terms.
     */
    int[] generate(int minLimit, int maxLimit);
}
