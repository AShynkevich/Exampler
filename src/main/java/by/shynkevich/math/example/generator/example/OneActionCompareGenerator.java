package by.shynkevich.math.example.generator.example;

import java.util.stream.Stream;
import javax.annotation.Resource;

import by.shynkevich.math.example.domain.ExampleType;
import by.shynkevich.math.example.domain.Operator;
import by.shynkevich.math.example.domain.example.TypicalExample;
import by.shynkevich.math.example.domain.term.OperatorTerm;
import by.shynkevich.math.example.domain.term.ValueTerm;
import by.shynkevich.math.example.generator.action.ActionFactory;

/**
 * Generates one action compare example
 * like: <b>a + b > c - d</b> and operation variations
 */
public class OneActionCompareGenerator implements ExampleGenerator {

    @Resource
    private ActionFactory actionFactory;

    private static final OperatorTerm[] OPERATOR_TERMS =
            Stream.of(Operator.LESS_THAN, Operator.MORE_THAN, Operator.EQUALS)
                    .map(value -> new OperatorTerm(value, true)).toArray(OperatorTerm[]::new);

    @Override
    public TypicalExample generate(int minLimit, int maxLimit) {
        Operator firstOperation = Operator.getOperationOperators()[RANDOM.nextInt(2)];
        Operator secondOperation = Operator.getOperationOperators()[RANDOM.nextInt(2)];

        int[] firstTerms = actionFactory.generateAction(firstOperation, minLimit, maxLimit);
        int[] secondTerms = actionFactory.generateAction(secondOperation, minLimit, maxLimit);

        int firstResult = firstTerms[2];
        int secondResult = secondTerms[2];

        OperatorTerm operatorTerm;
        if (firstResult < secondResult) {
            operatorTerm = OPERATOR_TERMS[0];
        } else if (firstResult > secondResult) {
            operatorTerm = OPERATOR_TERMS[1];
        } else {
            operatorTerm = OPERATOR_TERMS[2];
        }

        ValueTerm[] valueTerms = convertToTerms(0, firstTerms[0], firstTerms[1], secondTerms[0], secondTerms[1]);
        return new TypicalExample(
                ExampleType.ONE_ACTION_COMPARING,
                valueTerms[0], convertToTerm(firstOperation, false), valueTerms[1],
                operatorTerm,
                valueTerms[2], convertToTerm(secondOperation, false), valueTerms[3]);
    }
}
