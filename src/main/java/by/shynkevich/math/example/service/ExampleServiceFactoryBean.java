package by.shynkevich.math.example.service;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ExampleServiceFactoryBean implements FactoryBean<ExampleService> {

    @Override
    public ExampleService getObject() throws Exception {
        return new ExampleService();
    }

    @Override
    public Class<?> getObjectType() {
        return ExampleService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
