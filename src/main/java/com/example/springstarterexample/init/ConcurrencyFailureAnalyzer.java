package com.example.springstarterexample.init;

import com.example.springstarterexample.exception.ConcurrencyStartupException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

public class ConcurrencyFailureAnalyzer extends AbstractFailureAnalyzer<ConcurrencyStartupException> {
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, ConcurrencyStartupException cause) {
        return new FailureAnalysis(cause.getMessage(), "Укажите валидные значения для свойства", cause);
    }
}
