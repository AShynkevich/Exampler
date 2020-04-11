package by.shynkevich.math.example.domain.example;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import by.shynkevich.math.example.domain.ExampleType;
import by.shynkevich.math.example.domain.term.Term;

/**
 * Represents any type of example where the sequence of terms
 * are represented as completed math statement
 *
 * <p>
 * e.g. statement: 9 - 5 = 4
 * will be represented as List{Term(9), Term(-), Term(5), Term(=), Term(4)}
 * </p>
 */
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
