package by.shynkevich.math.example.domain;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TypicalExample {

    private final String id;
    private final List<Term<?>> terms;

    public TypicalExample(Term<?>... termsInts) {
        this.id = UUID.randomUUID().toString();
        this.terms = Stream.of(termsInts).collect(Collectors.toList());
    }

    public String getId() {
        return id;
    }

    public List<Term<?>> getTerms() {
        return terms;
    }
}
