package com.example.springstarterexample.init;

import com.example.springstarterexample.exception.ConcurrencyStartupException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class ConcurrencyEnvironmentPostProcessor implements EnvironmentPostProcessor {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        log.info("Вызов ConcurrencyEnvironmentPostProcessor");
        String enablePropertyValue = environment.getProperty("concurrency.enabled");
        boolean isBoolValue = Boolean.TRUE.toString().equals(enablePropertyValue) || Boolean.FALSE.toString().equals(enablePropertyValue);

        if (!isBoolValue) {
            throw new ConcurrencyStartupException("Ошибка при проверке свойства 'concurrency.enabled' ");
        }
    }
}
