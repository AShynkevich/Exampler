package by.shynkevich.math.example.generator.example;

import javax.annotation.Resource;

import by.shynkevich.math.example.domain.ExampleType;
import by.shynkevich.math.example.domain.Operator;
import by.shynkevich.math.example.domain.example.TypicalExample;
import by.shynkevich.math.example.domain.term.OperatorTerm;
import by.shynkevich.math.example.domain.term.ValueTerm;
import by.shynkevich.math.example.generator.action.ActionFactory;

public class TwoActionGenerator implements ExampleGenerator {

    @Resource
    private ActionFactory actionFactory;

    private static final OperatorTerm EQUAL_TERM = new OperatorTerm(Operator.EQUALS, false);

    @Override
    public TypicalExample generate(int minLimit, int maxLimit) {
        Operator firstOperation = Operator.getOperationOperators()[RANDOM.nextInt(2)];
        Operator secondOperation = Operator.getOperationOperators()[RANDOM.nextInt(2)];

        int[] firstTerms = actionFactory.generateAction(firstOperation, minLimit, maxLimit);
        int[] secondTerms = actionFactory.generateAction(secondOperation, minLimit, maxLimit, firstTerms[0]);

        ValueTerm[] valueTerms = convertToTerms(1, secondTerms[0], secondTerms[1], firstTerms[1], firstTerms[2]);
        OperatorTerm firstOperationTerm = convertToTerm(firstOperation, false);
        OperatorTerm secondOperationTerm = convertToTerm(secondOperation, false);
        return new TypicalExample(
                ExampleType.TWO_ACTION,
                valueTerms[0],
                secondOperationTerm,
                valueTerms[1],
                firstOperationTerm,
                valueTerms[2],
                EQUAL_TERM,
                valueTerms[3]);
    }
}
