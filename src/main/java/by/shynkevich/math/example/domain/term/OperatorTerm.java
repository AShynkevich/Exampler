package by.shynkevich.math.example.domain.term;

import by.shynkevich.math.example.domain.Operator;

public class OperatorTerm implements Term<Operator> {

    private final Operator value;
    private final boolean hidden;

    public OperatorTerm(Operator value, boolean hidden) {
        this.value = value;
        this.hidden = hidden;
    }

    @Override
    public boolean isHidden() {
        return hidden;
    }

    @Override
    public Operator getValue() {
        return value;
    }

    @Override
    public String show() {
        return value.getValue();
    }

    @Override
    public boolean equals(String obj) {
        return value.getValue().equals(obj);
    }
}
