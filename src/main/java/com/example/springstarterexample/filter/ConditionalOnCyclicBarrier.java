package com.example.springstarterexample.filter;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class ConditionalOnCyclicBarrier implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var enabled = Optional.ofNullable(context.getEnvironment().getProperty("concurrency.enabled"));
        var cyclicBarrierEnabled = Optional.ofNullable(context.getEnvironment().getProperty("concurrency.cyclic-barrier-enabled"));
        boolean hasProps = enabled.isPresent() && cyclicBarrierEnabled.isPresent();

        return hasProps && Boolean.parseBoolean(cyclicBarrierEnabled.get());
    }
}
