package by.shynkevich.math.example.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.annotation.Resource;

import by.shynkevich.math.example.generator.example.ExampleGeneratorFactory;
import by.shynkevich.math.example.domain.ExampleType;
import by.shynkevich.math.example.domain.Result;
import by.shynkevich.math.example.domain.example.TypicalExample;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    @Resource
    private ExampleGeneratorFactory factory;

    private Map<String, TypicalExample> exampleMap;
    private Set<String> success;
    private AtomicInteger failed;

    public List<TypicalExample> init(ExampleType type, int count, int minLimit, int maxLimit) {
        exampleMap = IntStream.range(0, count)
                .mapToObj(i -> factory.createExample(type, minLimit, maxLimit))
                .collect(Collectors.toMap(TypicalExample::getId, entry -> entry));
        success = new HashSet<>();
        failed = new AtomicInteger();
        return new ArrayList<>(exampleMap.values());
    }

    public boolean checkResult(String id, String value) {
        boolean isSuccess = exampleMap.get(id)
                .getTerms()
                .stream()
                .anyMatch(term -> term.isHidden() && term.equals(value));

        if (isSuccess) {
            success.add(id);
        } else {
            failed.incrementAndGet();
        }
        return isSuccess;
    }

    public Result getResult() {
        return new Result(failed.get(),
                success.size(),
                exampleMap.size(),
                exampleMap.size() == success.size());
    }
}
