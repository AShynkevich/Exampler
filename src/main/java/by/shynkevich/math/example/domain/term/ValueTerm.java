package by.shynkevich.math.example.domain.term;

public class ValueTerm implements Term<Integer> {

    private final Integer value;
    private final boolean hidden;

    public ValueTerm(int value, boolean hidden) {
        this.value = value;
        this.hidden = hidden;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isHidden() {
        return hidden;
    }

    @Override
    public String show() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(String value) {
        return this.value.equals(Integer.valueOf(value));
    }
}
