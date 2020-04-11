package by.shynkevich.math.example.generator.example;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import by.shynkevich.math.example.domain.Operator;
import by.shynkevich.math.example.domain.example.TypicalExample;
import by.shynkevich.math.example.domain.term.OperatorTerm;
import by.shynkevich.math.example.domain.term.ValueTerm;

/**
 * Represents example generator class.
 */
public interface ExampleGenerator {

    /**
     * Random number generator.
     */
    Random RANDOM = new Random();

    /**
     * Generates a @{@link TypicalExample} instance.
     *
     * @param minLimit min limit bound
     * @param maxLimit max limit bound
     * @return the @{@link TypicalExample} instance
     */
    TypicalExample generate(int minLimit, int maxLimit);

    /**
     * Covers bare number terms to @{@link ValueTerm}
     *
     * @param countToHide number values to hide
     * @param values      the array of bare number terms
     * @return the array of covered terms
     */
    default ValueTerm[] convertToTerms(int countToHide, int... values) {
        Set<Integer> indexes = RANDOM.ints(countToHide, 0, values.length).boxed().collect(Collectors.toSet());
        return IntStream.range(0, values.length)
                .mapToObj(i -> new ValueTerm(values[i], indexes.contains(i)))
                .toArray(ValueTerm[]::new);
    }

    /**
     * Converts any operator to @{@link OperatorTerm}.
     *
     * @param operator the {@link OperatorTerm} instance
     * @param hide     the hidden flag
     * @return the {@link OperatorTerm} instance
     */
    default OperatorTerm convertToTerm(Operator operator, boolean hide) {
        return new OperatorTerm(operator, hide);
    }
}
