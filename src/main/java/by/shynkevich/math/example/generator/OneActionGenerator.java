package by.shynkevich.math.example.generator;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import by.shynkevich.math.example.domain.Operator;
import by.shynkevich.math.example.domain.example.TypicalExample;
import by.shynkevich.math.example.domain.term.OperatorTerm;
import by.shynkevich.math.example.domain.term.ValueTerm;
import by.shynkevich.math.example.util.MathUtils;

public class OneActionGenerator implements ExampleGenerator {

    private static final OperatorTerm EQUAL_TERM = new OperatorTerm(Operator.EQUALS, false);
    private static final OperatorTerm[] OPERATOR_TERMS = Stream.of(Operator.MINUS, Operator.PLUS)
            .map(value -> new OperatorTerm(value, false)).toArray(OperatorTerm[]::new);

    @Override
    public TypicalExample generate(int minLimit, int maxLimit) {
        Operator operation = Operator.getOperationOperators()[RANDOM.nextInt(OPERATOR_TERMS.length)];
        int[] terms = MathUtils.generateAction(operation, minLimit, maxLimit);

        ValueTerm[] valueTerms = convertToTerms(1, terms[0], terms[1], terms[2]);
        OperatorTerm operatorTerm = new OperatorTerm(operation, false);
        return new TypicalExample(valueTerms[0], operatorTerm, valueTerms[1], EQUAL_TERM, valueTerms[2]);
    }
}
