package by.shynkevich.math.example.domain;

public enum Operator {

    PLUS("+"),
    MINUS("-"),
    EQUALS("="),
    MORE_THAN(">"),
    LESS_THAN("<");

    private String value;

    Operator(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
