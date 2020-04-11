package by.shynkevich.math.example.generator.action;

import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;

import by.shynkevich.math.example.domain.Operator;
import by.shynkevich.math.example.exception.NoOperationException;
import org.springframework.stereotype.Component;

/**
 * Math action factory.
 */
@Component
public class ActionFactory {

    private static final String NO_ACTION_FORMAT = "No action for [%s] operator";

    @Resource
    private Map<Operator, Action> actionStrategy;

    /**
     * Generates action by operator.
     *
     * @param operator the {@link Operator}.
     * @param minLimit min limit bound
     * @param maxLimit max limit bound
     * @return the array of terms
     */
    public int[] generateAction(Operator operator, int minLimit, int maxLimit) {
        Action action = actionStrategy.get(operator);
        if (Objects.isNull(action)) {
            throw new NoOperationException(String.format(NO_ACTION_FORMAT, operator.name()));
        }
        return action.generate(minLimit, maxLimit);
    }
}
