package by.shynkevich.math.example.domain.term;

import by.shynkevich.math.example.domain.Operation;

public class OperationTerm implements Term<Operation> {

    private final Operation value;
    private final boolean hidden;

    public OperationTerm(Operation value, boolean hidden) {
        this.value = value;
        this.hidden = hidden;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public Operation getValue() {
        return value;
    }

    @Override
    public String show() {
        return value.getValue();
    }
}
