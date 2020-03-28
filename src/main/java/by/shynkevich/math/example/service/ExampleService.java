package by.shynkevich.math.example.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import by.shynkevich.math.example.ExampleFactory;
import by.shynkevich.math.example.domain.example.TypicalExample;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    private Map<String, TypicalExample> exampleMap;
    private Set<String> success;

    public List<TypicalExample> init(int count, int minLimit, int maxLimit) {
        exampleMap = IntStream.range(0, count)
                .mapToObj(i -> ExampleFactory.createExample(minLimit, maxLimit))
                .collect(Collectors.toMap(TypicalExample::getId, entry -> entry));
        success = new HashSet<>();
        return new ArrayList<>(exampleMap.values());
    }

    public boolean checkResult(String id, Integer value) {
        boolean isSuccess = exampleMap.get(id)
                .getTerms()
                .stream()
                .anyMatch(term -> term.isHidden() && term.equals(value));

        if (isSuccess) {
            success.add(id);
        }
        return isSuccess;
    }

    public boolean allResolved() {
        return exampleMap.size() == success.size();
    }
}
