package by.shynkevich.math.example.domain;

public enum Operation {

    PLUS("+"), MINUS("-"), EQUALS("=");

    private String value;

    Operation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
