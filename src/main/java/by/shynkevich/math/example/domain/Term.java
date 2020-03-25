package by.shynkevich.math.example.domain;

public class Term {

    private final int value;
    private final boolean hidden;

    public Term(int value, boolean hidden) {
        this.value = value;
        this.hidden = hidden;
    }

    public int getValue() {
        return value;
    }

    public boolean isHidden() {
        return hidden;
    }
}
