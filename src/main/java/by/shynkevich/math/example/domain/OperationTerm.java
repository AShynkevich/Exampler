package by.shynkevich.math.example.domain;

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
