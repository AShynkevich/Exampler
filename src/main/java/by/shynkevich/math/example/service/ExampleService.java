package by.shynkevich.math.example.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.Resource;

import by.shynkevich.math.example.domain.ExampleType;
import by.shynkevich.math.example.domain.Result;
import by.shynkevich.math.example.domain.example.TypicalExample;
import by.shynkevich.math.example.generator.example.ExampleGeneratorFactory;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.Builder;
import org.springframework.stereotype.Service;

@Service
public class ExampleService implements Serializable {

    @Resource
    private ExampleGeneratorFactory factory;

    private Map<String, TypicalExample> exampleMap;
    private Set<String> success;
    private AtomicInteger failed;
    private boolean train;

    public void init(InitParamsBuilder builder) {
        this.exampleMap = IntStream.range(0, builder.count)
                .mapToObj(i -> factory.createExample(builder.type, builder.minLimit, builder.maxLimit))
                .collect(Collectors.toMap(TypicalExample::getId, entry -> entry));
        this.success = new HashSet<>();
        this.failed = new AtomicInteger();
        this.train = builder.train;
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

    public List<TypicalExample> getExamples() {
        return new ArrayList<>(exampleMap.values());
    }

    public boolean isTrain() {
        return train;
    }

    public static class InitParamsBuilder implements Builder<InitParamsBuilder> {

        private ExampleType type;
        private Integer count;
        private Integer minLimit;
        private Integer maxLimit;
        private Boolean train;

        public InitParamsBuilder withType(ExampleType pType) {
            this.type = pType;
            return this;
        }

        public InitParamsBuilder withCount(int pCount) {
            this.count = pCount;
            return this;
        }

        public InitParamsBuilder withMinLimit(int pMinLimit) {
            this.minLimit = pMinLimit;
            return this;
        }

        public InitParamsBuilder withMaxLimit(int pMaxLimit) {
            this.maxLimit = pMaxLimit;
            return this;
        }

        public InitParamsBuilder withTrain(boolean pTrain) {
            this.train = pTrain;
            return this;
        }

        @Override
        public InitParamsBuilder build() {
            Validate.notNull(type);
            Validate.notNull(count);
            Validate.notNull(minLimit);
            Validate.notNull(maxLimit);
            Validate.notNull(train);

            return this;
        }
    }
}
