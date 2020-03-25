package by.shynkevich.math.example.domain;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TypicalExample {

    private final String id;
    private final List<Term> terms;
    private final Operation operation;

    public TypicalExample(Operation operation, int toHide, int ...termsInts) {
        this.id = UUID.randomUUID().toString();
        this.operation = operation;
        this.terms = IntStream.range(0, termsInts.length)
                .mapToObj(i -> new Term(termsInts[i], toHide == i))
                .collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public Operation getOperation() {
        return operation;
    }

    public List<Term> getTerms() {
        return terms;
    }
}
