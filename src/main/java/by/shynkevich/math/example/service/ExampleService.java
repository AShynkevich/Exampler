package by.shynkevich.math.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import by.shynkevich.math.example.ExampleFactory;
import by.shynkevich.math.example.domain.TypicalExample;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    private Map<String, TypicalExample> exampleMap;

    public void init(int count, int minLimit, int maxLimit) {
        exampleMap = IntStream.range(0, count)
                .mapToObj(i -> ExampleFactory.createExample(minLimit, maxLimit))
                .collect(Collectors.toMap(TypicalExample::getId, entry -> entry));
    }

    public List<TypicalExample> getExamples() {
        return new ArrayList<>(exampleMap.values());
    }

    public boolean checkResult(String id, int value) {
        return exampleMap.get(id)
                .getTerms()
                .stream()
                .anyMatch(term -> term.isHidden() && value == term.getValue());
    }
}
