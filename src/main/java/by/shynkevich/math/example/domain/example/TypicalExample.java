package by.shynkevich.math.example.domain.example;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.shynkevich.math.example.domain.ExampleType;
import by.shynkevich.math.example.domain.term.Term;

public class TypicalExample {

    private final String id;
    private final List<Term<?>> terms;
    private final ExampleType type;

    public TypicalExample(ExampleType type, Term<?>... termsInts) {
        this.type = type;
        this.id = UUID.randomUUID().toString();
        this.terms = Stream.of(termsInts).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public List<Term<?>> getTerms() {
        return terms;
    }

    public ExampleType getType() {
        return type;
    }
}
